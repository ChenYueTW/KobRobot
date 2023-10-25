package frc.robot.subsystems;

import frc.robot.MotorIds;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    private TalonFX intakeMotor;
    private double intakeSpeed;

    public IntakeSubsystem() {
        this.intakeMotor = new TalonFX(MotorIds.Intake.Motor1);
        this.intakeMotor.setInverted(false);

        this.intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setDesiredState(double intakeSpeed) {
        this.intakeMotor.set(ControlMode.PercentOutput, intakeSpeed);
        SmartDashboard.putNumber("Intake-Speed: ", this.intakeSpeed);
    }

    public void stopModules() {
        this.intakeMotor.set(ControlMode.PercentOutput, 0);
    }
}