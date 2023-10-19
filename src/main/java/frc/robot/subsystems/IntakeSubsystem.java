package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.MotorIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor;
    private double intakeSpeed;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(MotorIds.Intake.Motor1, MotorType.kBrushless);

        intakeMotor.setSmartCurrentLimit(30);

        intakeMotor.setInverted(false);

        // kBrake & kCoast
        intakeMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(double intakeSpeed) {
        this.intakeMotor.set(intakeSpeed);
        SmartDashboard.putNumber("Intake-Speed: ", this.intakeSpeed);
    }

    public void intake(boolean direction){
        this.setDesiredState(Constants.Drive.INTAKE_SPEED * (direction ? 1 : -1));
    }

    public void stopModules() {
        intakeMotor.set(0);
    }
}