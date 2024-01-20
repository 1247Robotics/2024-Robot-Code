package frc.robot;

import com.revrobotics.CANSparkMax;

import frc.robot.Controllers.BaseController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class NewDrive {
    private BaseController controller;

    private final DifferentialDrive drive;

    private double X = 0;
    private double Y = 0;
    private double[] XBuffer = new double[5];
    private double[] YBuffer = new double[5];
    private double lastUpdate = 0;

    private boolean debug = false;

    public NewDrive(BaseController controller, CANSparkMax flMotor, CANSparkMax frMotor, CANSparkMax blMotor, CANSparkMax brMotor) {
        this.controller = controller;

        // Make the back motors slaves of the front motors
        blMotor.follow(flMotor);
        brMotor.follow(frMotor);

        drive = new DifferentialDrive(flMotor, frMotor);
    }

    public NewDrive(BaseController controller, MotorPack mp) {
        this(controller, mp.getFlMotor(), mp.getFrMotor(), mp.getBlMotor(), mp.getBrMotor());
    }

    public void pullController() {
        this.setMove(controller.getRightX(), controller.getLeftY());
        lastUpdate = System.currentTimeMillis();
    }

    public void setMove(double x, double y) {
        double Xavg = 0;
        for (double _x : XBuffer) {
            Xavg += _x;            
        }
        Xavg /= XBuffer.length;
        double Yavg = 0;
        for (double _y : YBuffer) {
            Yavg += _y;            
        }
        Yavg /= YBuffer.length;

        for (int i = 0; i < XBuffer.length - 1; i++) {
            XBuffer[i] = XBuffer[i + 1];
        }
        for (int i = 0; i < YBuffer.length - 1; i++) {
            YBuffer[i] = YBuffer[i + 1];
        }

        if (x+0.05 >= Xavg) {
            XBuffer[XBuffer.length - 1] = Xavg;
            X = Xavg;
        } else {
            XBuffer[XBuffer.length - 1] = x;
            X = x;
        }

        if (y+0.05 >= Yavg) {
            YBuffer[YBuffer.length - 1] = Yavg;
            Y = Yavg;
        } else {
            YBuffer[YBuffer.length - 1] = y;
            Y = y;
        }
        lastUpdate = System.currentTimeMillis();
    }

    public void drive() {
        if (System.currentTimeMillis() - lastUpdate > 250) {
            X = 0;
            Y = 0;
        }
        drive.arcadeDrive(Y, X);
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String arrToString(double[] arr) {
        String str = "[";
        for (double d : arr) {
            str += d + ", ";
        }
        str = str.substring(0, str.length() - 2);
        str += "]";
        return str;
    }

    public String toString() {
        if (debug) {
            return "X: " + X + ", Y: " + Y + ", Last Update: " + lastUpdate + ", Xarr: " + arrToString(XBuffer) + ", Yarr: " + arrToString(YBuffer);
        }
        return "X: " + X + ", Y: " + Y + ", Last Update: " + lastUpdate;
    }
        
    
}
