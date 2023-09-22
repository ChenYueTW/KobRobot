package frc.robot.commands;

import frc.robot.subsystems.ElvatorMotorSubsystem;
import frc.robot.Constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElvatorButtonCmd extends CommandBase {

    private final ElvatorMotorSubsystem ElvatorSubsystem;
    private final double Speed;


    public ElvatorButtonCmd(ElvatorMotorSubsystem subsystem, double Speed) {

        ElvatorSubsystem = subsystem;
        this.Speed = Speed;
        
        addRequirements(ElvatorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        ElvatorSubsystem.setDesiredState(Speed);
    }

    @Override
    public void end(boolean interrupted) {
        ElvatorSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}