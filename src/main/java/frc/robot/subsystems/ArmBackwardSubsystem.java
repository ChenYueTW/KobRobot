package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.MotorIds;

public class ArmBackwardSubsystem extends SubsystemBase{
    private final CANSparkMax backwardMotor;
    private final RelativeEncoder backwardEncoder;

    public ArmBackwardSubsystem() {
        this.backwardMotor = new CANSparkMax(MotorIds.Arm.Motor1, MotorType.kBrushless);
        this.backwardMotor.setSmartCurrentLimit(30);
        this.backwardMotor.setInverted(false);
        this.backwardMotor.setIdleMode(IdleMode.kBrake);
        this.backwardEncoder = this.backwardMotor.getEncoder();
        this.backwardEncoder.setPosition(0.0);
    }

    public void resetEncoder() {
        this.backwardEncoder.setPosition(0);
    }

    public void setDesiredStateBackward(double backwardSpeed) {
        double position = this.backwardEncoder.getPosition();
        if (position <= -58 && backwardSpeed < 0) {
            this.backwardMotor.set(0);

        } else if (position >= -5 && backwardSpeed > 0) {
            this.backwardMotor.set(0);

        } else {
            this.backwardMotor.set(backwardSpeed);
        }

        SmartDashboard.putNumber("BackwardArmPositon", this.backwardEncoder.getPosition());
        SmartDashboard.putNumber("Backward Speed", backwardSpeed);
    }
    
    public void stopModules() {
        this.backwardMotor.set(0);
    }
}
