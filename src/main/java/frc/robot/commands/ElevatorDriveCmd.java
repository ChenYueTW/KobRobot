package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorDriveCmd extends CommandBase {
    private final ElevatorSubsystem elevatorSubsystem;
    private final boolean direction;

    public ElevatorDriveCmd(ElevatorSubsystem subsystem, boolean direction) {
        this.elevatorSubsystem = subsystem;
        this.direction = direction;
        
        this.addRequirements(this.elevatorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        this.elevatorSubsystem.setDesiredState(Constants.Drive.ELEVATOR_SPEED * (direction ? 1 : -1));
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