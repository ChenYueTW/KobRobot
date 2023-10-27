package frc.robot;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoMethod {
    private final DriveMotorSubsystem driveMotorSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final ArmSubsystem armSubsystem;
    private final double driveSpeed = Constants.Auto.DRIVE_SPEED;
    private final double turnSpeed = Constants.Auto.TURN_SPEED;

    public AutoMethod(DriveMotorSubsystem driveMotorSubsystem, IntakeSubsystem intakeSubsystem, ArmSubsystem armSubsystem) {
        this.driveMotorSubsystem = driveMotorSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.armSubsystem = armSubsystem;
    }
    
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

    public void intakePullCube(){
        this.intakeSubsystem.setDesiredState(-Constants.Auto.INTAKE_SPEED);
    }

    public void intakePushCube(){
        this.intakeSubsystem.setDesiredState(Constants.Auto.INTAKE_SPEED);
    }

    public void intakePullCone() {
        this.intakeSubsystem.setDesiredState(Constants.Auto.INTAKE_SPEED);
    }

    public void intakePushCone() {
        this.intakeSubsystem.setDesiredState(-Constants.Auto.INTAKE_SPEED);
    }

    public void armForwardUP(){
        this.armSubsystem.setDesiredStateForward(Constants.Auto.ARM_FORWARD_SPEED);
    }

    public void armForwardDown(){
        this.armSubsystem.setDesiredStateForward(-Constants.Auto.ARM_FORWARD_SPEED);
    }

    public void armBackwardUP() {
        this.armSubsystem.setDesiredStateBackward(Constants.Auto.ARM_BACKWARD_SPEED);
    }

    public void armBackwardDown() {
        this.armSubsystem.setDesiredStateBackward(-Constants.Auto.ARM_BACKWARD_SPEED);
    }
}
