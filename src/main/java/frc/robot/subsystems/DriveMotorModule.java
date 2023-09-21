package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.robotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX
;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorModule {

    private VictorSPX Motor;
    private double speed_input;

    public DriveMotorModule(int Motor_Port, boolean reverse) {
        
        Motor = new VictorSPX(Motor_Port);
        Motor.setInverted(reverse);
        
    }

    public void setDesiredState(Double speed) {

        this.speed_input = speed * Constants.DriveConstants.CIMkSpeed;
        Motor.set(ControlMode.PercentOutput, this.speed_input);

        SmartDashboard.putNumber("CIM-Speed: ", this.speed_input);
        
    }

    public void stop() {
        Motor.set(ControlMode.PercentOutput, 0);
    }
}