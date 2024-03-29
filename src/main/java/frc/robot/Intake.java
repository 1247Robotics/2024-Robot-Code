package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;

import frc.robot.Controllers.Controller;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
    private Controller controller;
    private CANSparkMax intakeMotor;
    private SendableChooser<String> intakeChooser = new SendableChooser<>();
    private static final String intakeBrake = "Brake Mode";
    private static final String intakeCoast = "No resistance";

    public Intake(Controller controller){
        this.controller = controller;
        this.intakeMotor = new CANSparkMax(Definitions.intakeId,CANSparkLowLevel.MotorType.kBrushless);
        this.intakeMotor.setInverted(true);
        this.intakeMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        this.intakeMotor.burnFlash();
        // Intake
        intakeChooser.setDefaultOption(intakeBrake, intakeBrake);
        intakeChooser.addOption(intakeCoast, intakeCoast);
        SmartDashboard.putData(intakeChooser);
    }

    public void update(){
        String motorMode = intakeChooser.getSelected();
        switch (motorMode) {
            case intakeBrake:
                intakeMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
                break;
            case intakeCoast:
                intakeMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
                break;             
        }
        // if(controller.getRightBumper()){
        intakeMotor.set(controller.getRightY());
        // } else {
        //     intakeMotor.set(0);
        // }
    }

    public void update(double x) {
        intakeMotor.set(x);
    }
}
