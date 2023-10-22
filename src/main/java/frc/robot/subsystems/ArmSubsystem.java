package frc.robot.subsystems;

import frc.robot.MotorIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase{
    private final CANSparkMax armMotor;

    public ArmSubsystem() {
        this.armMotor = new CANSparkMax(MotorIds.Arm.Motor1, MotorType.kBrushless);

        this.armMotor.setSmartCurrentLimit(30);
        
        this.armMotor.setInverted(false);

        // kBrake & kCoast
        this.armMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setDesiredState(double armSpeed) {
        this.armMotor.set(armSpeed);
        SmartDashboard.putNumber("Arm-Speed: ", armSpeed);
    }

    public void stopModules() {
        this.armMotor.set(0);
    }
}