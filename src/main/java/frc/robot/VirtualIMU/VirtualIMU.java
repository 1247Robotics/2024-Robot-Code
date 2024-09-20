package frc.robot.VirtualIMU;

import javax.sound.midi.Track;

import com.revrobotics.CANSparkMax;


public class VirtualIMU {
    private static double x;
    private static double y;
    private static double theta;
    private static double wheelBase = 21.869;
    private static TrackMotor leftMotor;
    private static TrackMotor rightMotor;

    public VirtualIMU() {}

    public VirtualIMU(CANSparkMax left, CANSparkMax right) {
        x = 0;
        y = 0;
        theta = 0;
        leftMotor = new TrackMotor(6, 8.45);
        rightMotor = new TrackMotor(6, 8.45);
        leftMotor.setMotor(left);
        rightMotor.setMotor(right);
    }

    public double setGearRatio(double gearRatio) {
        leftMotor.setGearRatio(gearRatio);
        rightMotor.setGearRatio(gearRatio);
        return gearRatio;     
    }

    public double setWheelDiameter(double wheelDiameter) {
        leftMotor.setWheelDiameter(wheelDiameter);
        rightMotor.setWheelDiameter(wheelDiameter);
        return wheelDiameter;
    }

    public CANSparkMax getLeftMotor() {
        return leftMotor.getMotor();
    }

    public CANSparkMax getRightMotor() {
        return rightMotor.getMotor();
    }

    private double differentialToRadians(double left, double right) {
        return (right - left) / wheelBase;
    }

    private void updateTheta(double distanceTraveledLeft, double distanceTraveledRight) {
        double relativeTheta = differentialToRadians(distanceTraveledLeft, distanceTraveledRight);
        theta += relativeTheta;
    }

    private double avg(double a, double b) {
        return (a + b) / 2;
    }

    private void updateXY(double distanceTraveledLeft, double distanceTraveledRight) {
        double distance = avg(distanceTraveledLeft, distanceTraveledRight);
        x += distance * Math.cos(theta);
        y += distance * Math.sin(theta);
    }

    public void updateMotors() {
        double distanceTraveledLeft = leftMotor.updatePositionAndGetMovement();
        double distanceTraveledRight = rightMotor.updatePositionAndGetMovement();
        updateTheta(distanceTraveledLeft, distanceTraveledRight);
        updateXY(distanceTraveledLeft, distanceTraveledRight);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTheta() {
        return theta;
    }

    public double[] getPosition() {
        double[] position = { x, y, theta };
        return position;
    }

    public double getWheelBase() {
        return wheelBase;
    }

    public double getWheelDiameter() {
        return leftMotor.getWheelDiameter();
    }

    public double getGearRatio() {
        return leftMotor.getGearRatio();
    }

    public TrackMotor getLeftTrackMotor() {
        return leftMotor;
    }

    public TrackMotor getRightTrackMotor() {
        return rightMotor;
    }
}
