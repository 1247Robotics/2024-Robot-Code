package frc.robot;

import com.revrobotics.CANSparkMax;

import frc.robot.Controllers.Controller;
import frc.robot.VirtualIMU.VirtualIMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The NewDrive class represents the drivetrain of the robot.
 * It controls the movement of the robot using a controller input.
 */
public class Drive {
    private Controller controller;

    private final DifferentialDrive drive;
    private final VirtualIMU trackMotion;

    private double X = 0;
    private double Y = 0;
    private double[] XBuffer;
    private double[] YBuffer;
    private double lastUpdate = 0;

    private boolean debug = false;

    /**
     * Constructs a NewDrive object with the specified controller and motor configuration.
     * 
     * @param controller the controller used to control the robot's movement
     * @param flMotor the front left motor
     * @param frMotor the front right motor
     * @param blMotor the back left motor
     * @param brMotor the back right motor
     */
    public Drive(Controller controller, CANSparkMax flMotor, CANSparkMax frMotor, CANSparkMax blMotor, CANSparkMax brMotor) {
        this.controller = controller;

        // Make the back motors slaves of the front motors
        blMotor.follow(flMotor);
        brMotor.follow(frMotor);

        drive = new DifferentialDrive(flMotor, frMotor);
        trackMotion = new VirtualIMU(flMotor, frMotor);
    }

    /**
     * Constructs a NewDrive object with the specified controller and motor pack.
     * 
     * @param controller the controller used to control the robot's movement
     * @param mp the motor pack containing the front left, front right, back left, and back right motors
     */
    public Drive(Controller controller, MotorPack mp) {
        this(controller, mp.getFlMotor(), mp.getFrMotor(), mp.getBlMotor(), mp.getBrMotor());
    }

    public void updateMotors() {
        trackMotion.updateMotors();
    }

    public void setBuffers(int bufferSize) {
        XBuffer = new double[bufferSize];
        YBuffer = new double[bufferSize];
        System.out.println("Set buffers to "+bufferSize);
    }

    /**
     * Updates the controller input for the robot's movement.
     * This method should be called periodically to update the robot's movement.
     */
    public void pullController() {
        this.setMove(controller.getLeftX(), -controller.getLeftY());
        lastUpdate = System.currentTimeMillis();
    }

    public int getBufferSize() {
        return XBuffer.length;
    }
    
    /**
     * Shifts the XBuffer array to the left by one index.
     */
    private void shiftXBuffer() {
        for (int i = 0; i < XBuffer.length - 1; i++) {
            XBuffer[i] = XBuffer[i + 1];
        }
    }

    /**
     * Shifts the YBuffer array to the left by one index.
     */
    private void shiftYBuffer() {
        for (int i = 0; i < YBuffer.length - 1; i++) {
            YBuffer[i] = YBuffer[i + 1];
        }
    }

    /**
     * Calculates the average of the XBuffer array.
     *
     * @return the average of the XBuffer array
     */
    private double calculateXAvg() {
        double Xavg = 0;
        for (double _x : XBuffer) {
            Xavg += _x;            
        }
        Xavg /= XBuffer.length;
        return Xavg;
    }

    /**
     * Calculates the average of the YBuffer array.
     *
     * @return the average of the YBuffer array
     */
    private double calculateYAvg() {
        double Yavg = 0;
        for (double _y : YBuffer) {
            Yavg += _y;            
        }
        Yavg /= YBuffer.length;
        return Yavg;
    }

    /**
     * Updates the XBuffer array with the specified value.
     *
     * @param x the value to update the XBuffer array with
     */
    private void updateXBuffer(double x) {
        XBuffer[XBuffer.length - 1] = x;
    }   

    /**
     * Updates the YBuffer array with the specified value.
     *
     * @param y the value to update the YBuffer array with
     */
    private void updateYBuffer(double y) {
        YBuffer[YBuffer.length - 1] = y;
    }

    /**
     * Clamps the specified value between the specified minimum and maximum values.
     *
     * @param val the value to clamp
     * @param min the minimum value
     * @param max the maximum value
     * @return the clamped value
     */
    private double clamp(double val, double min, double max) {
        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    /**
     * Sets the desired movement of the robot where x is turning and y is forward/backward movement.
     * 
     * @param x the desired X-axis movement
     * @param y the desired Y-axis movement
     */
    public void setMove(double x, double y) {

        //#region Buffer movement to counter brownout (X axis)
        // Get the averages of the buffer arrays
        double Xavg = calculateXAvg();
        double Yavg = calculateYAvg();

        // Shift the buffer arrays
        shiftXBuffer();
        shiftYBuffer();

        // Fix stupid bullshit that sends the robot into objects for no reason
        boolean xAvgIsPositive = Xavg > 0.09;
        boolean xAvgIsNegative = Xavg < -0.09;

        // If the average is positive, clamp the value to 1 and check if the new value is greater than the average.
        // If it is, update the buffer array and recalculate the average.
        // If it isn't, just update the buffer array.
        // TLDR: Smooth the input if it is higher or equal to the average, otherwise send the raw input as the control.
        if (xAvgIsPositive) {
            x = clamp(x, 0, 1);
            if (x + 0.05 > Xavg) {
                updateXBuffer(x);
                X = calculateXAvg();
            } else {
                updateXBuffer(x);
                X = x;
            }

        // If the average is negative, clamp the value to -1 and check if the new value is less than the average
        // If it is, update the buffer array and recalculate the average.
        // If it isn't, just update the buffer array.
        // TLDR: Smooth the input if it is lower or equal to the average, otherwise send the raw input as the control.
        } else if (xAvgIsNegative) {
            x = clamp(x, -1, 0);
            if (x - 0.05 < Xavg) {
                updateXBuffer(x);
                X = calculateXAvg();
            } else {
                updateXBuffer(x);
                X = x;
            }
        } else {
            updateXBuffer(x);
            X = calculateXAvg();
        }
        //#endregion Buffer movement to counter brownout

        //#region Buffer movement to counter brownout (Y axis)
        boolean yAvgIsPositive = Yavg > 0.09;
        boolean yAvgIsNegative = Yavg < -0.09;

        // If the average is positive, clamp the value to 1 and check if the new value is greater than the average.
        // If it is, update the buffer array and recalculate the average.
        // If it isn't, just update the buffer array.
        // TLDR: Smooth the input if it is higher or equal to the average, otherwise send the raw input as the control.
        if (yAvgIsPositive) {
            y = clamp(y, 0, 1);
            if (y + 0.05 > Yavg) {
                updateYBuffer(y);
                Y = calculateYAvg();
            } else {
                updateYBuffer(y);
                Y = y;
            }
        // If the average is negative, clamp the value to -1 and check if the new value is less than the average
        // If it is, update the buffer array and recalculate the average.
        // If it isn't, just update the buffer array.
        // TLDR: Smooth the input if it is lower or equal to the average, otherwise send the raw input as the control.
        } else if (yAvgIsNegative) {
            y = clamp(y, -1, 0);
            if (y - 0.05 < Yavg) {
                updateYBuffer(y);
                Y = calculateYAvg();
            } else {
                updateYBuffer(y);
                Y = y;
            }
        } else {
            updateYBuffer(y);
            Y = calculateYAvg();
        }
        //#endregion Buffer movement to counter brownout
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Adds x and y to the existing X and Y. Should be called AFTER a standard input calculation
     * 
     * @param x
     * @param y
     */
    public void addToMovement(double x, double y) {
        X += x;
        Y += y;
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Drives the robot based on the current movement settings.
     * This method should be called periodically to update the robot's movement.
     */
    public void drive() {
        // Using information from > 250 ms ago is a bad idea so if it is, turn off the motors
        if (System.currentTimeMillis() - lastUpdate > 250) {
            X = 0;
            Y = 0;
        }
        drive.arcadeDrive(Y, X);
    }

    /**
     * Sets the debug mode of the NewDrive object.
     * 
     * @param debug true to enable debug mode, false otherwise
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * Converts an array of doubles to a string representation.
     * 
     * @param arr the array of doubles
     * @return the string representation of the array
     */
    public String arrToString(double[] arr) {
        String str = "[";
        for (double d : arr) {
            str += d + ", ";
        }
        str = str.substring(0, str.length() - 2);
        str += "]";
        return str;
    }

    /**
     * Returns a string representation of the NewDrive object.
     * 
     * @return the string representation of the NewDrive object
     */
    public String toString() {
        if (debug) {
            return "X: " + X + ", Y: " + Y + ", Last Update: " + lastUpdate + ", Xarr: " + arrToString(XBuffer) + ", Yarr: " + arrToString(YBuffer);
        }
        return "X: " + X + ", Y: " + Y + ", Last Update: " + lastUpdate;
    }
}
