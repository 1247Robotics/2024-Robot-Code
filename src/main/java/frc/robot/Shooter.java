package frc.robot;

import com.revrobotics.CANSparkMax;
// driverstation
import edu.wpi.first.wpilibj.DriverStation;

public class Shooter {
    private final CANSparkMax shooterMotor;
    private double lastPosition = 0;
    private int sinceStart = 0;
    private int failures = 0;
    private boolean lockout = false;

    public Shooter(int id) {
        this.shooterMotor = new CANSparkMax(id, CANSparkMax.MotorType.kBrushless);
        this.shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        this.shooterMotor.disableVoltageCompensation();
        this.shooterMotor.burnFlash();
    }

    public void update(double speed) {
        if (lockout) {
            return;
        }

        switch ((int) speed) {
            case 1:
                sinceStart++;
                if (sinceStart > 2) {
                    if (Math.abs(lastPosition - shooterMotor.getEncoder().getPosition()) < 0.1) {
                        // throw warnings
                        failures++;
                        if (failures > 20) {
                            if (failures > 40) {
                                DriverStation.reportError("SHOOTER LOCKOUT", false);
                                lockout = true;
                                
                            } else {
                                DriverStation.reportError("STOP SPINNING THE SHOOTER MOTOR", false);
                            }
                        } else {
                            DriverStation.reportWarning("Shooter motor is failing", false);
                        }
                    }
                }
            case 0:
                sinceStart = 0;
                failures = 0;
                break;
        }
        shooterMotor.set(speed);
    }
}
