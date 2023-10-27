package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveMotorSubsystem;

public class DriveJoystickCmd extends CommandBase {
	private final DriveMotorSubsystem driveSubsystem;
	private final XboxController controller;

	public DriveJoystickCmd(DriveMotorSubsystem subsystem, XboxController controller) {
		this.driveSubsystem = subsystem;
		this.controller = controller;
		addRequirements(this.driveSubsystem);
	}

	@Override
	public void initialize() {}

	@Override
	public void execute() {
		double moveSpeed = -MathUtil.applyDeadband(this.controller.getLeftY(), Constants.DEAD_BAND);
		double turnSpeed = MathUtil.applyDeadband(this.controller.getRightX(), Constants.DEAD_BAND);
		double brakes = 1 - MathUtil.applyDeadband(this.controller.getLeftTriggerAxis(), Constants.DEAD_BAND);

		double leftSpeed = (moveSpeed + turnSpeed) * brakes;
		double rightSpeed = (moveSpeed - turnSpeed) * brakes;
		this.driveSubsystem.driverMove(leftSpeed, rightSpeed);
	}

	@Override
	public void end(boolean interrupted) {
		driveSubsystem.stopModules();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
