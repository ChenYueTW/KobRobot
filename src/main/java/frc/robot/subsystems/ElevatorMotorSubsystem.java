package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.robotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorMotorSubsystem extends SubsystemBase{

    private CANSparkMax ElevatorMotor;
    private double Elevator_speed;

    public ElevatorMotorSubsystem() {

        ElevatorMotor = new CANSparkMax(robotMap.DriverPort.CAN_Port.Elevator1MotorPort, MotorType.kBrushless);

        ElevatorMotor.setSmartCurrentLimit(30);

        // kBrake 會在馬達停止後鎖住馬達
        // kCoast 會在馬達停止後讓馬達保持慣性
        ElevatorMotor.setIdleMode(IdleMode.kBrake); 
        ElevatorMotor.setInverted(false);
    }

    public void setDesiredState(Double speed) {
        // 將 speed 乘以速度最大值
        this.Elevator_speed = speed * Constants.DriveConstants.ElevatorSpeed; 
        ElevatorMotor.set(this.Elevator_speed);

        // 在 SmartDashboard 顯示當前轉速
        SmartDashboard.putNumber("ELevator-Speed: ", Elevator_speed);
        
    }

    public void stop() {
        ElevatorMotor.set(0);
    }
}