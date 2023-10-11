package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorDriveCmd extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;
    private final double elevatorSpeed;

    public ElevatorDriveCmd(ElevatorSubsystem subsystem, double speed) {
        this.elevatorSubsystem = subsystem;
        this.elevatorSpeed = speed;
        
        this.addRequirements(this.elevatorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.elevatorSubsystem.setDesiredState(this.elevatorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        this.elevatorSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}