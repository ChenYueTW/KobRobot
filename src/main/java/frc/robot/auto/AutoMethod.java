package frc.robot.auto;

import frc.robot.Constants;
import frc.robot.subsystems.ArmBackwardSubsystem;
import frc.robot.subsystems.ArmForwardSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoMethod {
    private final DriveMotorSubsystem driveMotorSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final ArmForwardSubsystem armForwardSubsystem;
    private final ArmBackwardSubsystem armBackwardSubsystem;
    private final double driveSpeed = Constants.Auto.DRIVE_SPEED;
    private final double turnSpeed = Constants.Auto.TURN_SPEED;

    public AutoMethod(DriveMotorSubsystem driveMotorSubsystem, IntakeSubsystem intakeSubsystem, ArmForwardSubsystem armForwardSubsystem, ArmBackwardSubsystem armBackwardSubsystem) {
        this.driveMotorSubsystem = driveMotorSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.armForwardSubsystem = armForwardSubsystem;
        this.armBackwardSubsystem = armBackwardSubsystem;
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
        this.armForwardSubsystem.setDesiredStateForward(-Constants.Auto.ARM_FORWARD_SPEED);
    }

    public void armForwardDown(){
        this.armForwardSubsystem.setDesiredStateForward(Constants.Auto.ARM_FORWARD_SPEED);
    }

    public void armBackwardUP() {
        this.armBackwardSubsystem.setDesiredStateBackward(-Constants.Auto.ARM_BACKWARD_SPEED);
    }

    public void armBackwardDown() {
        this.armBackwardSubsystem.setDesiredStateBackward(Constants.Auto.ARM_BACKWARD_SPEED);
    }
}
