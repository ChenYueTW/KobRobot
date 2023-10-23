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
        intakeMotor = new TalonFX(MotorIds.Intake.Motor1);
        intakeMotor.setInverted(false);

        // kBrake & kCoast
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setDesiredState(double intakeSpeed) {
        this.intakeMotor.set(ControlMode.PercentOutput, intakeSpeed);
        SmartDashboard.putNumber("Intake-Speed: ", this.intakeSpeed);
    }

    public void stopModules() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }
}