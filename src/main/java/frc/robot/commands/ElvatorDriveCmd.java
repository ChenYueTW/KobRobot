package frc.robot.commands;

import frc.robot.subsystems.ElvatorMotorSubsystem;
import frc.robot.Constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElvatorDriveCmd extends CommandBase {

    private final ElvatorMotorSubsystem ElvatorSubsystem;
    private final double Elvator_Speed;


    public ElvatorDriveCmd(ElvatorMotorSubsystem subsystem, double Elvator_Speed) {

        ElvatorSubsystem = subsystem;
        this.Elvator_Speed = Elvator_Speed;
        
        addRequirements(ElvatorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        ElvatorSubsystem.setDesiredState(Elvator_Speed);
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