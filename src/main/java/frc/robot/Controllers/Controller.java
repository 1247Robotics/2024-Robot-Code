package frc.robot.Controllers;

/**
 * The Controller class represents a generic controller with various input methods.
 */
public class Controller {
    private boolean fixTriggers = false;
    
    // Thresholds for analog inputs
    static double leftXThreshold = 0.0;
    static double leftYThreshold = 0.0;
    static double rightXThreshold = 0.0;
    static double rightYThreshold = 0.0;
    static double leftTriggerThreshold = 0.0;
    static double rightTriggerThreshold = 0.0;

    /**
     * Constructs a new Controller object.
     */
    public Controller() {
        // Do nothing
    }

    /**
     * Returns the value of the left X-axis joystick.
     * 
     * @return the value of the left X-axis joystick
     */
    public double getLeftX() {
        return 0;
    }

    /**
     * Returns the value of the left Y-axis joystick.
     * 
     * @return the value of the left Y-axis joystick
     */
    public double getLeftY() {
        return 0;
    }

    /**
     * Returns the value of the right X-axis joystick.
     * 
     * @return the value of the right X-axis joystick
     */
    public double getRightX() {
        return 0;
    }
    
    /**
     * Returns the value of the right Y-axis joystick.
     * 
     * @return the value of the right Y-axis joystick
     */
    public double getRightY() {
        return 0;
    }

    /**
     * Returns the value of the left trigger.
     * 
     * @return the value of the left trigger
     */
    public double getLeftTrigger() {
        return 0;
    }

    /**
     * Returns the value of the right trigger.
     * 
     * @return the value of the right trigger
     */
    public double getRightTrigger() {
        return 0;
    }

    /**
     * Returns the state of the left bumper.
     * 
     * @return the state of the left bumper
     */
    public boolean getLeftBumper() {
        return false;
    }

    /**
     * Returns the state of the right bumper.
     * 
     * @return the state of the right bumper
     */
    public boolean getRightBumper() {
        return false;
    }

    /**
     * Returns the state of the up direction on the D-pad.
     * 
     * @return the state of the up direction on the D-pad
     */
    public boolean getPadUp() {
        return false;
    }

    /**
     * Returns the state of the down direction on the D-pad.
     * 
     * @return the state of the down direction on the D-pad
     */
    public boolean getPadDown() {
        return false;
    }

    /**
     * Returns the state of the left direction on the D-pad.
     * 
     * @return the state of the left direction on the D-pad
     */
    public boolean getPadLeft() {
        return false;
    }

    /**
     * Returns the state of the right direction on the D-pad.
     * 
     * @return the state of the right direction on the D-pad
     */
    public boolean getPadRight() {
        return false;
    }

    /**
     * Returns the value of the D-pad as a floating-point number.
     * 
     * @return the value of the D-pad as a floating-point number
     */
    public double getPadAsFloat() {
        return 0;
    }

    /**
     * Returns the state of button A.
     * 
     * @return the state of button A
     */
    public boolean getButtonA() {
        return false;
    }

    /**
     * Returns the state of button B.
     * 
     * @return the state of button B
     */
    public boolean getButtonB() {
        return false;
    }

    /**
     * Returns the state of button X.
     * 
     * @return the state of button X
     */
    public boolean getButtonX() {
        return false;
    }

    /**
     * Returns the state of button Y.
     * 
     * @return the state of button Y
     */
    public boolean getButtonY() {
        return false;
    }

    /**
     * Returns the value of left joystick button
     * 
     * @return the value of left joystick button
     */
    public boolean getLeftJoystickButton() {
        return false;
    }

    /**
     * Returns the value of right joystick button
     * 
     * @return the value of right joystick button
     */
    public boolean getRightJoystickButton() {
        return false;
    }

    /**
     * Returns the value of the button at the specified index.
     * 
     * 0 = A
     * 1 = B
     * 2 = X
     * 3 = Y
     * 4 = Left Bumper
     * 5 = Right Bumper
     * 6 = Left Joystick Button
     * 7 = Right Joystick Button
     * 
     * @param input
     * @return
     */
    public boolean getButton(int input) {
        return switch (input) {
            case 0 -> getButtonA();
            case 1 -> getButtonB();
            case 2 -> getButtonX();
            case 3 -> getButtonY();
            case 4 -> getLeftBumper();
            case 5 -> getRightBumper();
            case 6 -> getLeftJoystickButton();
            case 7 -> getRightJoystickButton();
            default -> false;
        };
    }

    /**
     * Returns the value of the trigger at the specified index.
     * 
     * 0 = Left Trigger
     * 1 = Right Trigger
     * 
     * @param input
     * @return
     */
    public double getTrigger(int input) {
        return switch (input) {
            case 0 -> getLeftTrigger();
            case 1 -> getRightTrigger();
            default -> 0;
        };
    }

    /**
     * Returns the value of the joystick at the specified index.
     * 
     * 0 = Left X
     * 1 = Left Y
     * 2 = Right X
     * 3 = Right Y
     * 
     * @param input
     * @return
     */
    public double getJoystick(int input) {
        return switch (input) {
            case 0 -> getLeftX();
            case 1 -> getLeftY();
            case 2 -> getRightX();
            case 3 -> getRightY();
            default -> 0;
        };
    }

    /**
     * Fixes the stupid "-1 is the new zero" software design decision that killed a neo
     * 
     * @param fix
     */
    public void makeTriggersMakeSense(boolean fix) {
        fixTriggers = fix;
    }

    /**
     * Only should be called by children to automatically fix triggers being -1 when 0
     * 
     * @param input
     * @return
     */
    protected double correctTriggers(double input) {
        return fixTriggers ? (input + 1) / 2 : input;
    }

    protected double clamp(double x, double min, double max) {
        return Math.min(Math.max(x, min), max);
    }

    /**
     * Thresholds analog inputs
     * 
     * 0 = Left X
     * 1 = Left Y
     * 2 = Right X
     * 3 = Right Y
     * 4 = Left Trigger
     * 5 = Right Trigger
     *
     * @param input
     * @param inputNum
     * 
     * @return the thresholded value of the input
     */
    protected double threshold(double input, int inputNum) {
        return switch (inputNum) {
            case 0 -> Math.abs(input) > leftXThreshold ? input : 0;
            case 1 -> Math.abs(input) > leftYThreshold ? input : 0;
            case 2 -> Math.abs(input) > rightXThreshold ? input : 0;
            case 3 -> Math.abs(input) > rightYThreshold ? input : 0;
            case 4 -> Math.abs(input) > leftTriggerThreshold ? input : 0;
            case 5 -> Math.abs(input) > rightTriggerThreshold ? input : 0;
            default -> 0;
        };
    }

    /**
     * Reads current values of all inputs and stores them as the new zero
     * 
     */
    public void thisIsZero() {
        leftXThreshold = Math.abs(getLeftX());
        leftYThreshold = Math.abs(getLeftY());
        rightXThreshold = Math.abs(getRightX());
        rightYThreshold = Math.abs(getRightY());
        leftTriggerThreshold = Math.abs(getLeftTrigger());
        rightTriggerThreshold = Math.abs(getRightTrigger());
    }
}
