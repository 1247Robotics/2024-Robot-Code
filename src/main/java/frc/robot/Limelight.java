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
        return table.getEntry("tv").getInteger(0) == 1;
    }

    public double getThor() {
        return table.getEntry("thor").getDouble(0.0);
    }

    public double getTvert() {
        return table.getEntry("tvert").getDouble(0.0);
    }

    public double getTlong() {
        return table.getEntry("tlong").getDouble(0.0);
    }

    public double getTshort() {
        return table.getEntry("tshort").getDouble(0.0);
    }

    public String getTclass() {
        return table.getEntry("tclass").getString("none");
    }

    /**
     * Returns the distance to the target.
     * 
     * @param objectSize
     * @param cameraFov
     * @param objectPercentage
     * @return
     */
    private static double calcDistance(double objectSize, double cameraFov, double objectPercentage) {
        // Calculate the angle subtended by the object in degrees
        double angle = objectPercentage * cameraFov;

        // Calculate the half angle in radians
        double halfAngleRadians = Math.toRadians(angle / 2);

        // Use the tangent function to calculate the distance to the object
        double distance = objectSize / (2 * Math.tan(halfAngleRadians));

        return distance;
    }

    /**
     * Returns the distance to the target.
     * 
     * @return the distance to the target
     */
    public double getTargetDistance() {
        // Constants
        double cameraHorizontalFov = 63.3;
        double cameraVerticalFov = 49.7;
        double targetDiameter = 14; // inches

        double ta = getTa();
        double distance = (calcDistance(targetDiameter, cameraHorizontalFov, ta) + calcDistance(targetDiameter, cameraVerticalFov, ta))/2;

        return distance;
    }

    /**
     * Convert the degrees output by Tx and Ty to pixels
     * 
     * @param degrees
     * 
     * @return pixels
     */

    public double degreesToPixels(double degrees) {
        return degrees * 20;
    }

    
}
