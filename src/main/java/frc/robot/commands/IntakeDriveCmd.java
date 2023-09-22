package frc.robot.commands;

import frc.robot.subsystems.ElvatorMotorSubsystem;
import frc.robot.subsystems.IntakeMotorSubsystem;
import frc.robot.Constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeDriveCmd extends CommandBase {

    private final IntakeMotorSubsystem IntakeSubsystem;
    private final double Intake_Speed;


    public IntakeDriveCmd(IntakeMotorSubsystem subsystem, int i) {

        IntakeSubsystem = subsystem;
        this.Intake_Speed = i;
        
        addRequirements(IntakeSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        IntakeSubsystem.setDesiredState(Intake_Speed);
    }

    @Override
    public void end(boolean interrupted) {
        IntakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}