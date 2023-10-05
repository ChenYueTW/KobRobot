package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorModule {
    private final VictorSPX motor;
    private double driveSpeed;
    private double autoDriveSpeed;

    public DriveMotorModule(int motorPort, boolean reverse) {
        motor = new VictorSPX(motorPort);

        motor.setInverted(reverse);

        // Brake & Coast
        motor.setNeutralMode(NeutralMode.Brake);
    }

    public void setDesiredState(double driveSpeed) {
        this.driveSpeed = driveSpeed * Constants.DriveConstants.driveSpeed;
        
        motor.set(ControlMode.PercentOutput, this.driveSpeed);

        SmartDashboard.putNumber("CIM-Speed: ", this.driveSpeed); 
    }

    public void setAutoDesiredState(double autoDriveSpeed){
        this.autoDriveSpeed = autoDriveSpeed * Constants.DriveConstants.autoDirveSpeed;

        motor.set(ControlMode.PercentOutput, this.autoDriveSpeed);

        SmartDashboard.putNumber("Auto-Speed: ", this.autoDriveSpeed);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }
}