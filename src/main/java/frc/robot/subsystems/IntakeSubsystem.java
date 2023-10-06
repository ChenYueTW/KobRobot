package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    private CANSparkMax intakeMotor;
    private double intakeSpeed;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(RobotMap.DriverPort.Intake.Motor_1, MotorType.kBrushless);

        intakeMotor.setSmartCurrentLimit(30);

        intakeMotor.setInverted(false);

        // kBrake & kCoast
        intakeMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(Double intakeSpeed) {
        this.intakeSpeed = intakeSpeed * Constants.DriveConstants.intakeSpeed;

        intakeMotor.set(this.intakeSpeed);

        SmartDashboard.putNumber("Intake-Speed: ", this.intakeSpeed);
    }

    public void stop() {
        intakeMotor.set(0);
    }
}