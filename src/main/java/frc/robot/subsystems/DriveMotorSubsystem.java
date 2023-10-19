package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.MotorIds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveMotorSubsystem extends SubsystemBase { 
    private final DriveMotorModule leftMotor1;
    private final DriveMotorModule leftMotor2;
    private final DriveMotorModule rightMotor1;
    private final DriveMotorModule rightMotor2;

    public DriveMotorSubsystem() {
        this.leftMotor1 = new DriveMotorModule(MotorIds.PWM.LeftMotor1, false);
        this.leftMotor2 = new DriveMotorModule(MotorIds.PWM.LeftMotor2, false);
        this.rightMotor1 = new DriveMotorModule(MotorIds.PWM.RightMotor1, true);
        this.rightMotor2 = new DriveMotorModule(MotorIds.PWM.RightMotor2, true);
    }

    public void driverMove(double leftMotorSpeed, double rightMotorSpeed) {
        this.leftMotor1.setDesiredState(leftMotorSpeed);
        this.leftMotor2.setDesiredState(leftMotorSpeed);
        this.rightMotor1.setDesiredState(rightMotorSpeed);
        this.rightMotor2.setDesiredState(rightMotorSpeed);
    }

    public void autoDriveSystem(boolean direction){
        this.leftMotor1.setAutoDesiredState(Constants.Drive.DRIVE_SPEED_AUTO * (direction ? 1 : -1));
        this.leftMotor2.setAutoDesiredState(Constants.Drive.DRIVE_SPEED_AUTO * (direction ? 1 : -1));
        this.rightMotor1.setAutoDesiredState(Constants.Drive.DRIVE_SPEED_AUTO * (direction ? 1 : -1));
        this.rightMotor2.setAutoDesiredState(Constants.Drive.DRIVE_SPEED_AUTO * (direction ? 1 : -1));
    }

    public void stopModules() {
        this.leftMotor1.stop();
        this.leftMotor2.stop();
        this.rightMotor1.stop();
        this.rightMotor2.stop();
    }
}
