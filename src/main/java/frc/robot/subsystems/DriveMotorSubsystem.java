package frc.robot.subsystems;

import frc.robot.MotorIds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

public class DriveMotorSubsystem extends SubsystemBase { 
    private final DriveMotorModule leftMotor1;
    private final DriveMotorModule leftMotor2;
    private final DriveMotorModule rightMotor1;
    private final DriveMotorModule rightMotor2;
    private final AHRS gyro = new AHRS(SerialPort.Port.kUSB);
    
    public DriveMotorSubsystem() {
        this.leftMotor1 = new DriveMotorModule(MotorIds.PWM.LeftMotor1, false);
        this.leftMotor2 = new DriveMotorModule(MotorIds.PWM.LeftMotor2, false);
        this.rightMotor1 = new DriveMotorModule(MotorIds.PWM.RightMotor1, true);
        this.rightMotor2 = new DriveMotorModule(MotorIds.PWM.RightMotor2, true);
        // gyro.reset();
    }

    public double getPitch(){
        return Math.IEEEremainder(gyro.getPitch(), 360);
    }

    public void driverMove(double leftMotorSpeed, double rightMotorSpeed) {
        this.leftMotor1.setDesiredState(leftMotorSpeed);
        this.leftMotor2.setDesiredState(leftMotorSpeed);
        this.rightMotor1.setDesiredState(rightMotorSpeed);
        this.rightMotor2.setDesiredState(rightMotorSpeed);
    }

    public void autoDrive(double motorSpeed) {
        this.leftMotor1.setAutoDesiredState(motorSpeed);
        this.leftMotor2.setAutoDesiredState(motorSpeed);
        this.rightMotor1.setAutoDesiredState(motorSpeed);
        this.rightMotor2.setAutoDesiredState(motorSpeed);
    }

    public void autoTurnDrive(double leftSpeed, double rightSpeed) {
        this.leftMotor1.setAutoDesiredState(leftSpeed);
        this.leftMotor2.setAutoDesiredState(leftSpeed);
        this.rightMotor1.setAutoDesiredState(rightSpeed);
        this.rightMotor2.setAutoDesiredState(rightSpeed);
    }

    public void autoBalance(double balanceSpeed){
        this.leftMotor1.setAutoDesiredState(balanceSpeed);
        this.leftMotor2.setAutoDesiredState(balanceSpeed);
        this.rightMotor1.setAutoDesiredState(balanceSpeed);
        this.rightMotor2.setAutoDesiredState(balanceSpeed);
    }

    public void stopModules() {
        this.leftMotor1.stop();
        this.leftMotor2.stop();
        this.rightMotor1.stop();
        this.rightMotor2.stop();
    }
}
