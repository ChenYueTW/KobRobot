package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeDriveCmd extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final boolean direction;

    public IntakeDriveCmd(IntakeSubsystem subsystem, boolean direction) {
        this.intakeSubsystem = subsystem;
        this.direction = direction;
        
        this.addRequirements(this.intakeSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.intakeSubsystem.setDesiredState(Constants.Drive.INTAKE_SPEED * (direction ? 1 : -1));
    }

    @Override
    public void end(boolean interrupted) {
        this.intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}