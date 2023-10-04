package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.robotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotorSubsystem extends SubsystemBase{
    private CANSparkMax IntakeMotor;

    private double Intake_speed;

    public IntakeMotorSubsystem() {
        IntakeMotor = new CANSparkMax(robotMap.DriverPort.Intake_Port.IntakeMotorPort, MotorType.kBrushless);

        IntakeMotor.setSmartCurrentLimit(30);

        IntakeMotor.setInverted(false);

        // kBrake & kCoast
        IntakeMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(Double Intake_Speed) {
        this.Intake_speed = Intake_speed * Constants.DriveConstants.IntakeSpeed;

        IntakeMotor.set(Intake_speed);

        SmartDashboard.putNumber("Intake-Speed: ", Intake_speed);
    }

    public void stop() {
        IntakeMotor.set(0);
    }
}