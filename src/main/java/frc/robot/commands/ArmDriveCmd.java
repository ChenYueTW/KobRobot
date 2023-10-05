package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmDriveCmd extends CommandBase {
    private final ArmSubsystem armSubsystem;
    private final double armSpeed;

    public ArmDriveCmd(ArmSubsystem subsystem, double armSpeed) {
        this.armSubsystem = subsystem;
        this.armSpeed = armSpeed;
        
        this.addRequirements(this.armSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.armSubsystem.setDesiredState(this.armSpeed);
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