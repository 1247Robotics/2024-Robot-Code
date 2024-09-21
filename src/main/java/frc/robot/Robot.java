// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Controllers.P4Controller;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>(); 

  // STATIC VARIABLES ARE DEFINED IN src/main/java/frc/robot/Definitions.java

  private final MotorPack mp = new MotorPack(Definitions.flId, Definitions.frId, Definitions.blId, Definitions.brId);
  private final P4Controller controller = new P4Controller(0);  // P4Controller, XController, P5Controller, JoystickController, Controller (always 0)
  private final Drive driver = new Drive(controller, mp);
  private final Intake intake = new Intake(controller);
  private int bufferSize = 1;
  private final Shooter shooter = new Shooter(Definitions.shooterId);
  private int autoLoops = 0;

  private final Climbers climbers = new Climbers(Definitions.climberLeftId, Definitions.climberRightId);
  private final Limelight limel = new Limelight();

  private int autoDoing = 0;
  private int autoDoingFor = 0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Auto
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("Chase Note", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    SmartDashboard.putNumber("Auto forward time (s)", 2);

    // Shooter
    SmartDashboard.putNumber("Shooter speed", Definitions.shooterSpeed);
    SmartDashboard.putNumber("Shooter idle speed", Definitions.shooterIdle);

    // Auto move
    SmartDashboard.putNumber("Autonomous X move", 0.5);
    SmartDashboard.putNumber("Autonomous Y move", 0);

    SmartDashboard.putNumber("Target Distance", 0.0);

    
    

    driver.setBuffers(bufferSize); // Definitions.*  |  BOX_BOT, LOW, MEDIUM, HIGH, EXTREME
    controller.makeTriggersMakeSense(true);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Target Distance", limel.getTargetDistance());
    driver.updateMotors(); // Ensure that the position calculations are always up to date
    double[] pos = driver.getVirtualIMU().getPosition();
    
    // System.out.println("("+pos[0]+", "+pos[1]+"), Theta: "+pos[2]);

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    autoLoops = 0;
    autoDoing = 0;
    autoDoingFor = 0;
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        if (limel.hasTarget()) {
          driver.setMove(limel.getTx() / 45, Math.max(limel.getTargetDistance()/10, 1));
          driver.drive();
        }
        break;
      case kDefaultAuto:        
        if (autoLoops < 3 * 50) {
          driver.setMove(0.0, -0.5);
          driver.drive();
          autoLoops++;
        } else {
          if (limel.hasTarget() && limel.getTclass().equals("ignore") && limel.getTargetDistance() < 15) {
            driver.setMove(0.0, -0.5);
            driver.drive();
          }
          driver.setMove(0,0);
          driver.drive();
        }
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    driver.pullController();
    if (controller.getButtonY() && limel.hasTarget()) {
      driver.addToMovement(limel.getTx() / 50, 0);
    }
    driver.drive();
    intake.update();
    shooter.update(controller.getButtonA() ? Definitions.shooterSpeed : Definitions.shooterIdle);
    if (controller.getLeftTrigger() > 0.05) {
      climbers.setSpeed(controller.getLeftTrigger());
    } else if (controller.getRightTrigger() > 0.05) {
      climbers.setSpeed(-controller.getRightTrigger());
    } else {
      climbers.setSpeed(0);
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
