package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
		double moveSpeed = -this.controller.getLeftY();
		double turnSpeed = this.controller.getRightX();

		double leftSpeed = moveSpeed + turnSpeed;
		double rightSpeed = moveSpeed - turnSpeed;
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
