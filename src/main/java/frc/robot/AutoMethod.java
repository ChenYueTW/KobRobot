package frc.robot;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoMethod {
    private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final double driveSpeed = Constants.Auto.DRIVE_SPEED;
    private final double turnSpeed = Constants.Auto.TURN_SPEED;
    
    public void forwardDrive(){
        this.driveMotorSubsystem.autoDrive(Constants.Auto.DRIVE_SPEED);
    }

    public void backwardDrvie(){
        this.driveMotorSubsystem.autoDrive(-Constants.Auto.DRIVE_SPEED);
    }

    public void turnForwardLeftDrive() {
        double leftSpeed = this.driveSpeed - this.turnSpeed;
        double rightSpeed = this.driveSpeed + this.turnSpeed;
        this.driveMotorSubsystem.autoTurnDrive(leftSpeed, rightSpeed);
    }

    public void turnForwardRightDrive() {
        double leftSpeed = this.driveSpeed + this.turnSpeed;
        double rightSpeed = this.driveSpeed - this.turnSpeed;
        this.driveMotorSubsystem.autoTurnDrive(leftSpeed, rightSpeed);
    }

    public void turnBackwardLeftDrive() {
        double leftSpeed = -this.driveSpeed - this.turnSpeed;
        double rightSpeed = -this.driveSpeed + this.turnSpeed;
        this.driveMotorSubsystem.autoTurnDrive(leftSpeed, rightSpeed);
    }

    public void turnBackwardRightDrive() {
        double leftSpeed = -this.driveSpeed + this.turnSpeed;
        double rightSpeed = -this.driveSpeed - this.turnSpeed;
        this.driveMotorSubsystem.autoTurnDrive(leftSpeed, rightSpeed);
    }

    public void leftRotation() {
        this.driveMotorSubsystem.autoTurnDrive(-this.driveSpeed, this.driveSpeed);
    }

    public void rightRotation() {
        this.driveMotorSubsystem.autoTurnDrive(this.driveSpeed, -this.driveSpeed);
    }

    public void pullSystem(){
        this.intakeSubsystem.setDesiredState(Constants.Auto.INTAKE_SPEED);
    }

    public void pushSystem(){
        this.intakeSubsystem.setDesiredState(-Constants.Auto.INTAKE_SPEED);
    }

    public void armUp(){
        this.armSubsystem.setDesiredState(Constants.Auto.ARM_SPEED);
    }

    public void armDown(){
        this.armSubsystem.setDesiredState(-Constants.Auto.ARM_SPEED);
    }
}
