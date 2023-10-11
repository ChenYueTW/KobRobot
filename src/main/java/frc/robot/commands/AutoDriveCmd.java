package frc.robot.commands;

import frc.robot.subsystems.DriveMotorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDriveCmd extends CommandBase {
    private final DriveMotorSubsystem driveMotorSubsystem;
    private final double autoDriveSpeed;
 
    public AutoDriveCmd(DriveMotorSubsystem subsystem, double autoDriveSpeed){
        this.driveMotorSubsystem = subsystem;
        this.autoDriveSpeed = autoDriveSpeed;

        this.addRequirements(this.driveMotorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.driveMotorSubsystem.autoDriveMove(this.autoDriveSpeed, this.autoDriveSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        this.driveMotorSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}