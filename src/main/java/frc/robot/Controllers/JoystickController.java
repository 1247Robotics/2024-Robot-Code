package frc.robot.Controllers;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickController extends Controller {
    private final Joystick joystick;

    public JoystickController(int id) {
        joystick = new Joystick(id);
    }

    @Override
    public double getLeftY() {
        return joystick.getY();
    }

    @Override
    public double getRightX() {
        return joystick.getZ();
    }

    @Override
    public boolean getRightBumper() {
        return joystick.getTrigger();
    }

    @Override
    public boolean getPadUp() {
        return joystick.getPOV() == 0;
    }

    @Override
    public boolean getPadDown() {
        return joystick.getPOV() == 180;
    }

    @Override
    public boolean getPadLeft() {
        return joystick.getPOV() == 270;
    }

    @Override
    public boolean getPadRight() {
        return joystick.getPOV() == 90;
    }

    @Override
    public double getPadAsFloat() {
        return joystick.getPOV();
    }
    
}
