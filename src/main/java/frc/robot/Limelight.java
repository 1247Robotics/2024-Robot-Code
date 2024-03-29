package frc.robot;

// Needs networktables
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * The Limelight class represents a Limelight camera.
 */

public class Limelight {
    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;

    /**
     * Constructs a new Limelight object.
     */
    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
    }

    /**
     * Returns the horizontal offset from the crosshair to the target.
     * 
     * @return the horizontal offset from the crosshair to the target
     */
    public double getTx() {
        return tx.getDouble(0.0);
    }

    /**
     * Returns the vertical offset from the crosshair to the target.
     * 
     * @return the vertical offset from the crosshair to the target
     */
    public double getTy() {
        return ty.getDouble(0.0);
    }

    /**
     * Returns the target area.
     * 
     * @return the target area
     */
    public double getTa() {
        return ta.getDouble(0.0);
    }

    /**
     * Returns whether the Limelight has any valid targets.
     * 
     * @return whether the Limelight has any valid targets
     */
    public boolean hasTarget() {
        return ta.getDouble(0.0) > 0;
    }

    private static double calcDistance(double objectSize, double cameraFov, double objectPercentage) {
        // Convert object percentage to a decimal 
        double objectPercentageDecimal = objectPercentage / 100;

        // Calculate the angle subtended by the object in degrees
        double angle = objectPercentageDecimal * cameraFov;

        // Calculate the half angle in radians
        double halfAngleRadians = Math.toRadians(angle / 2);

        // Use the tangent function to calculate the distance to the object
        double distance = objectSize / (2 * Math.tan(halfAngleRadians));

        return distance;
    }

    public double getTargetDistance() {
        // Constants
        double cameraHorizontalFov = 63.3;
        double cameraVerticalFov = 49.7;
        double targetDiameter = 14; // inches

        double ta = getTa();
        double distance = (calcDistance(targetDiameter, cameraHorizontalFov, ta) + calcDistance(targetDiameter, cameraVerticalFov, ta))/2;

        return distance;
    }

    
}
