package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.robotMap;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElvatorMotorSubsystem extends SubsystemBase{

    private CANSparkMax ElvatorMotor1;
    private CANSparkMax ElvatorMotor2;

    private double Elvator_speed;

    public ElvatorMotorSubsystem() {

        ElvatorMotor1 = new CANSparkMax(robotMap.DriverPort.Elvator_Port.Elvator1MotorPort, MotorType.kBrushless);
        ElvatorMotor2 = new CANSparkMax(robotMap.DriverPort.Elvator_Port.Elvator2MotorPort, MotorType.kBrushless);

        ElvatorMotor1.setSmartCurrentLimit(30);
        ElvatorMotor2.setSmartCurrentLimit(30);

        // kBrake & kCoast
        ElvatorMotor1.setIdleMode(IdleMode.kBrake); 
        ElvatorMotor1.setInverted(false);

        ElvatorMotor2.setIdleMode(IdleMode.kBrake);
        ElvatorMotor2.setInverted(false);
    }

    public void setDesiredState(Double Elvator_Speed) {

        this.Elvator_speed = Elvator_Speed * Constants.DriveConstants.ElvatorSpeed; 
        ElvatorMotor1.set(this.Elvator_speed);
        ElvatorMotor2.set(this.Elvator_speed);

        SmartDashboard.putNumber("Elvator-Speed: ", Elvator_speed);
        
    }

    public void stop() {
        ElvatorMotor1.set(0);
        ElvatorMotor2.set(0);
    }
}