package frc.robot;

import com.revrobotics.CANSparkMax;

public class Climbers {
    CANSparkMax leftClimber;
    CANSparkMax rightClimber;

    public Climbers(int leftId, int rightId) {
        leftClimber = new CANSparkMax(leftId, CANSparkMax.MotorType.kBrushless);
        rightClimber = new CANSparkMax(rightId, CANSparkMax.MotorType.kBrushless);
        leftClimber.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightClimber.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void setSpeed(double speed) {
        leftClimber.set(speed);
        rightClimber.set(speed);
    }

    public CANSparkMax getLeftClimber() {
        return leftClimber;
    }

    public CANSparkMax getRightClimber() {
        return rightClimber;
    }

    
}
