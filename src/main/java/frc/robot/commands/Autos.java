package frc.robot.commands;

import frc.robot.subsystems.DriveMotorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;


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
        AutoSubsystem.kDrivermove(0.15, 0.15);
        new WaitCommand(2);
        AutoSubsystem.stopModules();
        new WaitCommand(2);
        AutoSubsystem.kDrivermove(-0.15, -0.15);
        new WaitCommand(2);
        AutoSubsystem.stopModules();
        new WaitCommand(2);
    }

    @Override
    public void end(boolean interrupted) {
       AutoSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}