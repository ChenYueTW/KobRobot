package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorModule {

    private VictorSPX Motor;

    private double speed_input;
    private double Auto_Speed;

    public DriveMotorModule(int Motor_Port, boolean reverse) {
        Motor = new VictorSPX(Motor_Port);

        Motor.setInverted(reverse);

        // Brake & Coast
        Motor.setNeutralMode(NeutralMode.Brake);
    }

    public void setDesiredState(Double Drive_Speed) {
        this.speed_input = Drive_Speed * Constants.DriveConstants.CIMkSpeed;
        
        Motor.set(ControlMode.PercentOutput, this.speed_input);

        SmartDashboard.putNumber("CIM-Speed: ", this.speed_input); 
    }

    public void setAutoDesiredState(Double Auto_Speed){
        this.Auto_Speed = Auto_Speed * Constants.DriveConstants.AutoSpeed;

        Motor.set(ControlMode.PercentOutput, this.Auto_Speed);

        SmartDashboard.putNumber("Auto-Speed: ", this.Auto_Speed);
    }

    public void stop() {
        Motor.set(ControlMode.PercentOutput, 0);
    }
}