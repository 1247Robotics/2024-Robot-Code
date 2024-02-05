package frc.robot;

// spark max
import com.revrobotics.CANSparkMax;

public class Climber {
    CANSparkMax lClimber;
    CANSparkMax rClimber;
    double multiplier = 1;

    public Climber(int lClimberId, int rClimberId) {
        lClimber = new CANSparkMax(lClimberId, Definitions.brushless);
        rClimber = new CANSparkMax(rClimberId, Definitions.brushless);
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
