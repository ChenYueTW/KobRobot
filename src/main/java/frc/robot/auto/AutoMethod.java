package frc.robot.auto;

import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoMethod {
    private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    
    public void forwardDrive(){
        this.driveMotorSubsystem.autoDrive(Constants.Auto.DRIVE_SPEED);
    }

    public void backwardDrvie(){
        this.driveMotorSubsystem.autoDrive(-Constants.Auto.DRIVE_SPEED);
    }

    public void turnLeftDrive() {
        this.driveMotorSubsystem.autoTrunLeftDrive(Constants.Auto.TURN_SPEED);
    }

    public void turnRightDrive() {
        this.driveMotorSubsystem.autoTurnRightDrive(Constants.Auto.TURN_SPEED);
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
