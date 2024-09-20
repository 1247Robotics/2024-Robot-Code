package frc.robot.PathFollowing;

import frc.robot.VirtualIMU.VirtualIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.ControlType;


public class PathFollowing {
    private CANSparkMax left;
    private CANSparkMax right;
    private VirtualIMU imu;
    public PathFollowing() {
        imu = new VirtualIMU();
        left = imu.getLeftMotor();
        right = imu.getRightMotor();
    }

    /** Calculate the end position of the motor to move the robot x distance */
    private double calculateNecessaryMovement(double moveDistance) {
        double wheelDiameter = imu.getWheelDiameter();
        double gearRatio = imu.getGearRatio();
        double distance = moveDistance / (wheelDiameter * Math.PI);
        return distance * 360 / gearRatio;
    }

    /**
     * Turn robot by a specific amount, irrespective of the current theta
     * 
     * @param angle
     */
    private void turn(double angle) {
        // Calculate the distance each wheel needs to travel
        double distanceL = angle * imu.getWheelBase() / 2;
        double distanceR = -angle * imu.getWheelBase() / 2;

        // Calculate the encoder position each wheel needs to move to
        double angleL = imu.getLeftTrackMotor().getEncoderMovementForDistance(distanceL);
        double angleR = imu.getRightTrackMotor().getEncoderMovementForDistance(distanceR);


        // Move the robot
        left.getPIDController().setReference(angleL, ControlType.kPosition);
        right.getPIDController().setReference(angleR, ControlType.kPosition);
    }

    /** 
     * Turn robot to a specific angle based on the current theta
     * 
     * @param angle
     */
    private void turnTo(double angle) {
        double currentTheta = imu.getTheta();
        double angleToTurn = angle - currentTheta;
        turn(angleToTurn);
    }

    /**
     * Move the robot a specific distance
     * 
     * @param distance
     */
    public void go(double distance) {
        // Calculate the encoder position each wheel needs to move to
        double angleL = imu.getLeftTrackMotor().getEncoderMovementForDistance(distance);
        double angleR = imu.getRightTrackMotor().getEncoderMovementForDistance(distance);

        // Move the robot
        left.getPIDController().setReference(angleL, ControlType.kPosition);
        right.getPIDController().setReference(angleR, ControlType.kPosition);
    }

    public void goDirect(double x, double y) {
        double currentX = imu.getX();
        double currentY = imu.getY();
        double currentTheta = imu.getTheta();

        double distance = Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2));
        double angle = Math.atan2(y - currentY, x - currentX);

        turnTo(angle);
        go(distance); 
    }


    
}
