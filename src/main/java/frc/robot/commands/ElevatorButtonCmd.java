package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorMotorSubsystem;
import frc.robot.Constants;

public class ElevatorButtonCmd extends CommandBase {

    private final ElevatorMotorSubsystem ElevatorSubsystem;
    private final double Speed;


    public ElevatorButtonCmd(ElevatorMotorSubsystem subsystem, double Speed) {

        ElevatorSubsystem = subsystem;
        this.Speed = Speed;
        
        addRequirements(ElevatorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        ElevatorSubsystem.setDesiredState(Speed);;
    }

    @Override
    public void end(boolean interrupted) {
        ElevatorSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}