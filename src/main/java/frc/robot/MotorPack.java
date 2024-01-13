package frc.robot;

import com.revrobotics.CANSparkMax;

public class MotorPack {
    private final CANSparkMax flMotor;
    private final CANSparkMax frMotor;
    private final CANSparkMax blMotor;
    private final CANSparkMax brMotor;

    public MotorPack(int flId, int frId, int blId, int brId) {
        flMotor = new CANSparkMax(flId, CANSparkMax.MotorType.kBrushless);
        frMotor = new CANSparkMax(frId, CANSparkMax.MotorType.kBrushless);
        blMotor = new CANSparkMax(blId, CANSparkMax.MotorType.kBrushless);
        brMotor = new CANSparkMax(brId, CANSparkMax.MotorType.kBrushless);
    }

    public CANSparkMax getFlMotor() {
        return flMotor;
    }

    public CANSparkMax getFrMotor() {
        return frMotor;
    }

    public CANSparkMax getBlMotor() {
        return blMotor;
    }

    public CANSparkMax getBrMotor() {
        return brMotor;
    }

    public String toString() {
        return "flMotor: " + flMotor.getDeviceId() + ", frMotor: " + frMotor.getDeviceId() + ", blMotor: " + blMotor.getDeviceId() + ", brMotor: " + brMotor.getDeviceId();
    }
    
    
}
