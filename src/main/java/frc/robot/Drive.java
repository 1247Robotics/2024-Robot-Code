package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drive {
  private final Joystick leftStick = new Joystick(Definitions.joystickId);
  private final XboxController xbox = new XboxController(Definitions.xboxId);
  private final PS4Controller ps5 = new PS4Controller(Definitions.ps5Id);

  private final DifferentialDrive drive;

  private double moveX = 0;
  private double moveY = 0;
  private double lastUpdate = 0;

  public Drive(CANSparkMax flMotor, CANSparkMax frMotor, CANSparkMax blMotor, CANSparkMax brMotor) {

    // Make the back motors slaves of the front motors
    blMotor.follow(flMotor);
    brMotor.follow(frMotor);

    drive = new DifferentialDrive(flMotor, frMotor);
  }

  public Drive(MotorPack mp) {
    this(mp.getFlMotor(), mp.getFrMotor(), mp.getBlMotor(), mp.getBrMotor());
  }

  public void pullMoveController(int controller) {
    // 0 = joystick, 1 = xbox

    if (controller == 0) {
      moveX = leftStick.getX();
      moveY = leftStick.getY();
      lastUpdate = System.currentTimeMillis();
    } else if (controller == 1) {
      moveX = xbox.getLeftX();
      moveY = xbox.getLeftY();
      lastUpdate = System.currentTimeMillis();
    } else if (controller == 2) {
      moveX = ps5.getLeftX();
      moveY = ps5.getLeftY();
      lastUpdate = System.currentTimeMillis();
    }
  }

  public void setMove(double x, double y) {
    moveX = x;
    moveY = y;
    lastUpdate = System.currentTimeMillis();
  }

  public void drive() {
    if (System.currentTimeMillis() - lastUpdate > 250) {
      moveX = 0;
      moveY = 0;
    }

    drive.arcadeDrive(moveY, moveX);
  }

  public String toString() {
    return "moveX: " + moveX + ", moveY: " + moveY + ", lastUpdate: " + lastUpdate;
  }
}
