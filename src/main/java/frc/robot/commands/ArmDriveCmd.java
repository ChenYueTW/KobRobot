package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmDriveCmd extends CommandBase {
    private final ArmSubsystem armSubsystem;
    private final boolean direction;

    public ArmDriveCmd(ArmSubsystem subsystem, boolean direction) {
        this.armSubsystem = subsystem;
        this.direction = direction;
        
        this.addRequirements(this.armSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.armSubsystem.setDesiredState(Constants.Drive.ARM_SPEED * (direction ? 1 : -1));
    }

    @Override
    public void end(boolean interrupted) {
        this.armSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}