package frc.robot.commands;

import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.Constants;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveJoystickCmd extends CommandBase {
    private final DriveMotorSubsystem driveMotorSubsystem;
    private final Supplier<Double> speedSupplier, turnSupplier;

    public DriveJoystickCmd(DriveMotorSubsystem subsystem, Supplier<Double> speedFunction, Supplier<Double> turnFunction) {
        this.driveMotorSubsystem = subsystem;
        this.speedSupplier = speedFunction;
        this.turnSupplier = turnFunction;
        
        this.addRequirements(this.driveMotorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double driveSpeed = speedSupplier.get();
        double turnSpeed = turnSupplier.get() * Constants.DriveConstants.turnSpeed;

        double leftDriveSpeed = driveSpeed - turnSpeed;
        double rightDriveSpeed = driveSpeed + turnSpeed;
        this.driveMotorSubsystem.driverMove(leftDriveSpeed, rightDriveSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        this.driveMotorSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}