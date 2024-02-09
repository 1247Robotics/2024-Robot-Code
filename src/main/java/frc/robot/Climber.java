package frc.robot;

// spark max
import com.revrobotics.CANSparkMax;

public class Climber {
    CANSparkMax lClimber;
    CANSparkMax rClimber;
    double multiplier = 1;

    public Climber(int lClimberId, int rClimberId) {
        lClimber = new CANSparkMax(lClimberId, CANSparkMax.MotorType.kBrushless);
        rClimber = new CANSparkMax(rClimberId, CANSparkMax.MotorType.kBrushless);
    }

    public void set(double speed) {
        lClimber.set(speed * multiplier);
        rClimber.set(speed * multiplier);
    }

    public void stop() {
        lClimber.set(0);
        rClimber.set(0);
    }

    public void setMultiplier(double mult) {
        multiplier = mult;
    }

    
}
