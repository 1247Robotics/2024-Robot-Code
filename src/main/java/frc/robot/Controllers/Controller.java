package frc.robot.Controllers;

/**
 * The Controller class represents a generic controller with various input methods.
 */
public class Controller {
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
}
