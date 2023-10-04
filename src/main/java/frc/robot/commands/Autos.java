package frc.robot.commands;

import frc.robot.subsystems.DriveMotorSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
        AutoSubsystem.kDrivermove(0.6, 0.6);
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