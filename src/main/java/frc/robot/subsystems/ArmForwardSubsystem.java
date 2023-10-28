package frc.robot.subsystems;

import frc.robot.MotorIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmForwardSubsystem extends SubsystemBase{
    private final CANSparkMax forwardMotor;
    private final RelativeEncoder forwardEncoder;

    public ArmForwardSubsystem() {
        this.forwardMotor = new CANSparkMax(MotorIds.Arm.Motor2, MotorType.kBrushless);
        this.forwardMotor.setSmartCurrentLimit(30);
        this.forwardMotor.setInverted(false);
        this.forwardMotor.setIdleMode(IdleMode.kBrake);
        this.forwardEncoder = this.forwardMotor.getEncoder();
        this.forwardEncoder.setPosition(0.0);
    }

    public void resetEncoder() {
        this.forwardEncoder.setPosition(0.0);
    }

    public void setDesiredStateForward(double armSpeed) {
        double position = this.forwardEncoder.getPosition();
        if (position <= -60 && armSpeed < 0) {
            this.forwardMotor.set(0);

        } else if (position >= -2 && armSpeed > 0) {
            this.forwardMotor.set(0);

        } else {
            this.forwardMotor.set(armSpeed);
        }

        SmartDashboard.putNumber("ForwardArmPositon", this.forwardEncoder.getPosition());
        SmartDashboard.putNumber("Forward Speed", armSpeed);
    }

    public void stopModules() {
        this.forwardMotor.set(0);
    }
}