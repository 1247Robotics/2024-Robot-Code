// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Controllers.P4Controller;
import frc.robot.Controllers.P5Controller;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;

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
  private final PDP pdp = new PDP();
  private int bufferSize = 1;
  private int untilRecheck = 0;
  private DigitalOutput led = new DigitalOutput(0);
  private DigitalOutput led2 = new DigitalOutput(1);
  private AnalogInput lightSensor = new AnalogInput(0);
  private boolean read = false;
  private boolean light = true;
  
  private final Shooter shooter = new Shooter(Definitions.shooterId);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

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
    // System.out.println("Voltage: " + pdp.getV());
    if (pdp.isBrownout()){ 

      if (untilRecheck <= 0) {
        bufferSize++;
        driver.setBuffers(bufferSize);
      }
      untilRecheck = 4;
      
    }
    untilRecheck = untilRecheck > 0 ? untilRecheck - 1 : 0;

    if (light) {
      if (read) {
        int lightLevel = lightSensor.getValue();
        System.out.println("Light Level: " + lightLevel);
      }
      read = !read;
      led.set(read);
    }
    light = !light;

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
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
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
    driver.drive();
    intake.update();
    shooter.update(controller.getButtonA() ? -1 : 0);
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
