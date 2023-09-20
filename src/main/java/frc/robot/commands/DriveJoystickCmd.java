package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.Constants;

public class DriveJoystickCmd extends CommandBase {

    private final DriveMotorSubsystem MotorSubsystem;
    private final Supplier<Double> SpeedFunction, TurnFuncion;


    public DriveJoystickCmd(DriveMotorSubsystem subsystem, Supplier<Double> SpeedFunction, Supplier<Double> TurnFuncion) {

        MotorSubsystem = subsystem;
        this.SpeedFunction = SpeedFunction;
        this.TurnFuncion = TurnFuncion;
        
        addRequirements(MotorSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double Speed = SpeedFunction.get();
        double Turn = TurnFuncion.get();

        double LeftkMotorSpeed = Speed + Turn;
        double RightkMotorSpeed = Speed - Turn;
        MotorSubsystem.kDrivermove(LeftkMotorSpeed, RightkMotorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        MotorSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}