package frc.robot.Controllers;

import edu.wpi.first.wpilibj.PS5Controller;

public class P5Controller extends Controller {
    private final PS5Controller ps5;

    public P5Controller(int id) {
        ps5 = new PS5Controller(id);
    }

    @Override
    public double getLeftX() {
        // return ps5.getLeftX();
        return super.threshold(leftTriggerThreshold, 0);
    }

    @Override
    public double getLeftY() {
        // return ps5.getLeftY();
        return super.threshold(leftYThreshold, 1);
    }

    @Override
    public double getRightX() {
        // return ps5.getRightX();
        return super.threshold(rightXThreshold, 2);
    }

    @Override
    public double getRightY() {
        // return ps5.getRightY();
        return super.threshold(rightYThreshold, 3);
    }

    @Override
    public double getLeftTrigger() {
        // return ps5.getL2Axis();
        return super.correctTriggers(super.threshold(leftTriggerThreshold, 4));
    }

    @Override
    public double getRightTrigger() {
        // return ps5.getR2Axis();
        return super.correctTriggers(super.threshold(rightTriggerThreshold, 5));
    }

    @Override
    public boolean getLeftBumper() {
        return ps5.getL1Button();
    }

    @Override
    public boolean getRightBumper() {
        return ps5.getR1Button();
    }

    @Override
    public boolean getPadUp() {
        return ps5.getPOV() == 0;
    }

    @Override
    public boolean getPadDown() {
        return ps5.getPOV() == 180;
    }

    @Override
    public boolean getPadLeft() {
        return ps5.getPOV() == 270;
    }

    @Override
    public boolean getPadRight() {
        return ps5.getPOV() == 90;
    }

    @Override
    public double getPadAsFloat() {
        return ps5.getPOV();
    }

    @Override
    public boolean getButtonA() {
        return ps5.getCrossButton();
    }

    @Override
    public boolean getButtonB() {
        return ps5.getCircleButton();
    }

    @Override
    public boolean getButtonX() {
        return ps5.getSquareButton();
    }

    @Override
    public boolean getButtonY() {
        return ps5.getTriangleButton();
    }

    @Override
    public boolean getLeftJoystickButton() {
        return ps5.getL3Button();
    }

    @Override
    public boolean getRightJoystickButton() {
        return ps5.getR3Button();
    }
}
