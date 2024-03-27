package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;

import frc.robot.Controllers.Controller;

public class Intake {
    private Controller controller;
    private CANSparkMax intakeMotor;

    public Intake(Controller controller){
        this.controller = controller;
        this.intakeMotor = new CANSparkMax(Definitions.intakeId,CANSparkLowLevel.MotorType.kBrushless);
        this.intakeMotor.setInverted(true);
        this.intakeMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        this.intakeMotor.burnFlash();
    }

    public void update(){
        if(controller.getRightBumper()){
            intakeMotor.set(controller.getLeftY());
        } else {
            intakeMotor.set(0);
        }
    }
}
