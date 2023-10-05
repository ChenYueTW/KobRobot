package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase{
    private CANSparkMax armMotor;
    private double armSpeed;

    public ArmSubsystem() {
        armMotor = new CANSparkMax(RobotMap.DriverPort.Arm.Motor_1, MotorType.kBrushless);

        armMotor.setSmartCurrentLimit(30);
        
        armMotor.setInverted(false);

        // kBrake & kCoast
        armMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(Double armSpeed) {
        this.armSpeed = armSpeed * Constants.DriveConstants.armSpeed;
        armMotor.set(this.armSpeed);

        SmartDashboard.putNumber("Arm-Speed: ", armSpeed);
    }

    public void stop() {
        armMotor.set(0);
    }
}