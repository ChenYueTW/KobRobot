package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    private CANSparkMax elevatorMotor1;
    private CANSparkMax elevatorMotor2;
    private double elevatorSpeed;

    public ElevatorSubsystem() {
        elevatorMotor1 = new CANSparkMax(RobotMap.DriverPort.Elevator.Motor_1, MotorType.kBrushless);
        elevatorMotor2 = new CANSparkMax(RobotMap.DriverPort.Elevator.Motor_2, MotorType.kBrushless);

        elevatorMotor1.setSmartCurrentLimit(30);
        elevatorMotor2.setSmartCurrentLimit(30);

        elevatorMotor1.setInverted(false);
        elevatorMotor2.setInverted(false);

        // kBrake & kCoast
        elevatorMotor1.setIdleMode(IdleMode.kBrake); 
        elevatorMotor2.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(Double elevatorSpeed) {
        this.elevatorSpeed = elevatorSpeed * Constants.DriveConstants.elevatorSpeed;

        elevatorMotor1.set(this.elevatorSpeed);
        elevatorMotor2.set(this.elevatorSpeed);

        SmartDashboard.putNumber("Elvator-Speed: ", this.elevatorSpeed);
    }

    public void stop() {
        elevatorMotor1.set(0);
        elevatorMotor2.set(0);
    }
}