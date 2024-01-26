package frc.robot.Controllers;

import edu.wpi.first.wpilibj.PS4Controller;

public class P4Controller extends Controller {
    private final PS4Controller ps4;

    public P4Controller(int id) {
        ps4 = new PS4Controller(id);
    }

    @Override
    public double getLeftX() {
        return ps4.getLeftX();
    }

    @Override
    public double getLeftY() {
        return ps4.getLeftY();
    }

    @Override
    public double getRightX() {
        return ps4.getRightX();
    }

    @Override
    public double getRightY() {
        return ps4.getRightY();
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
}
