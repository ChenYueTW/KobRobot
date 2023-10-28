package frc.robot.subsystems;

import frc.robot.MotorIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmForwardSubsystem extends SubsystemBase{
    private final CANSparkMax forwardMotor;

    public ArmForwardSubsystem() {
        this.forwardMotor = new CANSparkMax(MotorIds.Arm.Motor2, MotorType.kBrushless);
        this.forwardMotor.setSmartCurrentLimit(30);
        this.forwardMotor.setInverted(false);
        this.forwardMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredStateForward(double armSpeed) {
        this.forwardMotor.set(armSpeed);
        SmartDashboard.putNumber("Forward Speed", armSpeed);
    }

    public void stopModules() {
        this.forwardMotor.set(0);
    }
}