package frc.robot;

import com.revrobotics.CANSparkMax;

public class Shooter {
    private final CANSparkMax shooterMotor;

    public Shooter(int id) {
        this.shooterMotor = new CANSparkMax(id, CANSparkMax.MotorType.kBrushless);
        this.shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        this.shooterMotor.burnFlash();
    }

    public void update(double speed) {
        shooterMotor.set(speed);
    }
}
