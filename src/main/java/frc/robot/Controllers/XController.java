package frc.robot.Controllers;

import edu.wpi.first.wpilibj.XboxController;

public class XController extends Controller{
    private final XboxController xbox;

    public XController(int id) {
        xbox = new XboxController(id);
    }

    @Override
    public double getLeftX() {
        return xbox.getLeftX();
    }

    @Override
    public double getLeftY() {
        return xbox.getLeftY();
    }

    @Override
    public double getRightX() {
        return xbox.getRightX();
    }

    @Override
    public double getRightY() {
        return xbox.getRightY();
    }

    @Override
    public double getLeftTrigger() {
        return xbox.getLeftTriggerAxis();
    }

    @Override
    public double getRightTrigger() {
        return xbox.getRightTriggerAxis();
    }

    @Override
    public boolean getLeftBumper() {
        return xbox.getLeftBumper();
    }

    @Override
    public boolean getRightBumper() {
        return xbox.getRightBumper();
    }

    @Override
    public boolean getPadUp() {
        return xbox.getPOV() == 0;
    }

    @Override
    public boolean getPadDown() {
        return xbox.getPOV() == 180;
    }

    @Override
    public boolean getPadLeft() {
        return xbox.getPOV() == 270;
    }

    @Override
    public boolean getPadRight() {
        return xbox.getPOV() == 90;
    }

    @Override
    public double getPadAsFloat() {
        switch (xbox.getPOV()) {
            case -1:
                return -1;
            default:
                return xbox.getPOV() / 360.0;

        }        
    }

    @Override
    public boolean getButtonA() {
        return xbox.getAButton();
    }

    @Override
    public boolean getButtonB() {
        return xbox.getBButton();
    }

    @Override
    public boolean getButtonX() {
        return xbox.getXButton();
    }

    @Override
    public boolean getButtonY() {
        return xbox.getYButton();
    }
}
