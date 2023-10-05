package frc.robot.commands;

import frc.robot.subsystems.DriveMotorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Autos extends CommandBase {

    private final DriveMotorSubsystem AutoSubsystem;
 
    public Autos(DriveMotorSubsystem subsystem){
        AutoSubsystem = subsystem;

        addRequirements(AutoSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        AutoSubsystem.kAutoDriverMove(0.3);
    }

    @Override
    public void end(boolean interrupted) {
        this.AutoSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}