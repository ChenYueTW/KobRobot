package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeDriveCmd extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final double intakeSpeed;

    public IntakeDriveCmd(IntakeSubsystem subsystem, double intakeSpeed) {
        this.intakeSubsystem = subsystem;
        this.intakeSpeed = intakeSpeed;
        
        this.addRequirements(this.intakeSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.intakeSubsystem.setDesiredState(this.intakeSpeed);
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