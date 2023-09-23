package frc.robot.commands;

import frc.robot.subsystems.ArmMotorSubsystem;
import frc.robot.Constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class ArmDriveCmd extends CommandBase {

    private final ArmMotorSubsystem ArmSubsystem;
    private final double ArmSpeed;


    public ArmDriveCmd(ArmMotorSubsystem subsystem, double ArmSpeed) {

        ArmSubsystem = subsystem;
        this.ArmSpeed = ArmSpeed;
        
        addRequirements(ArmSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        ArmSubsystem.setDesiredState(ArmSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        ArmSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}