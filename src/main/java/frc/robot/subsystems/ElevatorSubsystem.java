package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.MotorIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    private final CANSparkMax elevatorMotor1;
    private final CANSparkMax elevatorMotor2;

    public ElevatorSubsystem() {
        this.elevatorMotor1 = new CANSparkMax(MotorIds.Elevator.Motor1, MotorType.kBrushless);
        this.elevatorMotor2 = new CANSparkMax(MotorIds.Elevator.Motor2, MotorType.kBrushless);

        this.elevatorMotor1.setSmartCurrentLimit(30);
        this.elevatorMotor2.setSmartCurrentLimit(30);

        this.elevatorMotor1.setInverted(false);
        this.elevatorMotor2.setInverted(false);

        // kBrake & kCoast
        this.elevatorMotor1.setIdleMode(IdleMode.kBrake);
        this.elevatorMotor2.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(Double elevatorSpeed) {
        this.elevatorMotor1.set(elevatorSpeed);
        this.elevatorMotor2.set(elevatorSpeed);

        SmartDashboard.putNumber("Elevator-Speed: ", elevatorSpeed);
    }

    public void stop() {
        this.elevatorMotor1.set(0.0);
        this.elevatorMotor2.set(0.0);
    }
}