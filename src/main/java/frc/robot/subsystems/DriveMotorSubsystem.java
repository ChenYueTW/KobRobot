package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveMotorSubsystem extends SubsystemBase { 
    private final DriveMotorModule leftMotor1;
    private final DriveMotorModule leftMotor2;
    private final DriveMotorModule rightMotor1;
    private final DriveMotorModule rightMotor2;

    public DriveMotorSubsystem() {
        leftMotor1 = new DriveMotorModule(RobotMap.DriverPort.PWM.Left_Motor_1, false);
        leftMotor2 = new DriveMotorModule(RobotMap.DriverPort.PWM.Left_Motor_2, false);
        rightMotor1 = new DriveMotorModule(RobotMap.DriverPort.PWM.Right_Motor_1, true);
        rightMotor2 = new DriveMotorModule(RobotMap.DriverPort.PWM.Right_Motor_2, true);
    }

    public void driverMove(double leftMotorSpeed, double rightMotorSpeed) {
        leftMotor1.setDesiredState(leftMotorSpeed);
        leftMotor2.setDesiredState(leftMotorSpeed);
        rightMotor1.setDesiredState(rightMotorSpeed);
        rightMotor2.setDesiredState(rightMotorSpeed);
    }
    
    public void autoDriveMove(double autoDriveSpeed){
        leftMotor1.setAutoDesiredState(autoDriveSpeed);
        leftMotor2.setAutoDesiredState(autoDriveSpeed);
        rightMotor1.setAutoDesiredState(autoDriveSpeed);
        rightMotor2.setAutoDesiredState(autoDriveSpeed);
    }

    public void stopModules() {
        leftMotor1.stop();
        leftMotor2.stop();
        rightMotor1.stop();
        rightMotor2.stop();
    }
}