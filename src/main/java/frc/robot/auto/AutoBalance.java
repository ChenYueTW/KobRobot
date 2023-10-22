package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveMotorSubsystem;

public class AutoBalance extends CommandBase {
	private final DriveMotorSubsystem autoBalanceSubsystem;

	public AutoBalance(DriveMotorSubsystem autoBalanceSubsystem) {
		this.autoBalanceSubsystem = autoBalanceSubsystem;
		addRequirements(this.autoBalanceSubsystem);
	}

	@Override
	public void initialize() {}

	@Override
	public void execute() {
		double pitchRate = Math.sin(this.autoBalanceSubsystem.getPitch() * (Math.PI / 180.0)) * -1;
		this.autoBalanceSubsystem.autoBalance(pitchRate);
		Timer.delay(0.05);
		SmartDashboard.putNumber("pitchRate", pitchRate);
	}

	@Override
	public void end(boolean interrupted) {
		this.autoBalanceSubsystem.stopModules();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
