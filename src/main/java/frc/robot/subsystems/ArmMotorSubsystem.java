package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.robotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmMotorSubsystem extends SubsystemBase{

    private CANSparkMax ArmMotor;

    private double Arm_speed;

    public ArmMotorSubsystem() {

        ArmMotor = new CANSparkMax(robotMap.DriverPort.Arm_Port.Arm1MotorPort, MotorType.kBrushless);

        ArmMotor.setSmartCurrentLimit(30);

        // kBrake & kCoast
        ArmMotor.setIdleMode(IdleMode.kBrake);

        ArmMotor.setInverted(false);
    }

    public void setDesiredState(Double ArmSpeed) {

        this.Arm_speed = ArmSpeed * Constants.DriveConstants.ArmSpeed;
        ArmMotor.set(Arm_speed);

        SmartDashboard.putNumber("Arm-Speed: ", Arm_speed);
        
    }

    public void stop() {
        ArmMotor.set(0);
    }
}