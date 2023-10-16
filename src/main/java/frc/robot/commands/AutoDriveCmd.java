package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveMotorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDriveCmd extends CommandBase {
    private final DriveMotorSubsystem driveMotorSubsystem;
	private final boolean direction;
 
    public AutoDriveCmd(DriveMotorSubsystem subsystem, boolean direction){
        this.driveMotorSubsystem = subsystem;
		this.direction = direction;

        this.addRequirements(this.driveMotorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.driveMotorSubsystem.autoDriveMove(Constants.Drive.DRIVE_SPEED_AUTO * (direction ? 1 : -1));
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