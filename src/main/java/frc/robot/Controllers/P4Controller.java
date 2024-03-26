package frc.robot.Controllers;

import edu.wpi.first.wpilibj.PS4Controller;

public class P4Controller extends Controller {
    private final PS4Controller ps4;

    public P4Controller(int id) {
        ps4 = new PS4Controller(id);
    }

    @Override
    public double getLeftX() {
        double x = ps4.getLeftX();
        x = Math.abs(x) < 0.05 ? 0 : x;
        return x;
    }

    @Override
    public double getLeftY() {
        double y = ps4.getLeftY();
        y = Math.abs(y) < 0.05 ? 0 : y;
        return y;
    }

    @Override
    public double getRightX() {
        double x = ps4.getRightX();
        x = Math.abs(x) < 0.05 ? 0 : x;
        return x;
    }

    @Override
    public double getRightY() {
        double y = ps4.getRightY();
        y = Math.abs(y) < 0.05 ? 0 : y;
        return y;
    }

    @Override
    public double getLeftTrigger() {
        return ps4.getL2Axis();
    }

    @Override
    public double getRightTrigger() {
        return ps4.getR2Axis();
    }

    @Override
    public boolean getLeftBumper() {
        return ps4.getL1Button();
    }

    @Override
    public boolean getRightBumper() {
        return ps4.getR1Button();
    }

    @Override
    public boolean getPadUp() {
        return ps4.getPOV() == 0;
    }

    @Override
    public boolean getPadDown() {
        return ps4.getPOV() == 180;
    }

    @Override
    public boolean getPadLeft() {
        return ps4.getPOV() == 270;
    }

    @Override
    public boolean getPadRight() {
        return ps4.getPOV() == 90;
    }

    @Override
    public double getPadAsFloat() {
        return ps4.getPOV();
    }

    @Override
    public boolean getLeftJoystickButton() {
        return ps4.getL3Button();
    }

    @Override
    public boolean getRightJoystickButton() {
        return ps4.getR3Button();
    }
}
