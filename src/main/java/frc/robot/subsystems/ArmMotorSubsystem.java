package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.robotMap;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmMotorSubsystem extends SubsystemBase{

    private CANSparkMax ArmMotor1;
    private CANSparkMax ArmMotor2;

    private double Arm_speed;

    public ArmMotorSubsystem() {

        ArmMotor1 = new CANSparkMax(robotMap.DriverPort.Arm_Port.Arm1MotorPort, MotorType.kBrushless);
        ArmMotor2 = new CANSparkMax(robotMap.DriverPort.Arm_Port.Arm2MotorPort, MotorType.kBrushless);

        ArmMotor1.setSmartCurrentLimit(30);
        ArmMotor2.setSmartCurrentLimit(30);

        // kBrake & kCoast
        ArmMotor1.setIdleMode(IdleMode.kBrake);
        ArmMotor2.setIdleMode(IdleMode.kBrake);

        ArmMotor1.setInverted(false);
        ArmMotor2.setInverted(false);
    }

    public void setDesiredState(Double ArmSpeed) {

        this.Arm_speed = ArmSpeed * Constants.DriveConstants.ArmSpeed;
        ArmMotor1.set(Arm_speed);
        ArmMotor2.set(Arm_speed);

        SmartDashboard.putNumber("Arm-Speed: ", Arm_speed);
        
    }

    public void stop() {
        ArmMotor1.set(0);
        ArmMotor2.set(0);
    }
}