package frc.robot.commands;

import frc.robot.subsystems.DriveMotorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDriveCmd extends CommandBase {
    private final DriveMotorSubsystem driveMotorSubsystem;
 
    public AutoDriveCmd(DriveMotorSubsystem subsystem){
        this.driveMotorSubsystem = subsystem;

        this.addRequirements(driveMotorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.driveMotorSubsystem.autoDriveMove(0.3, 0.3);
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