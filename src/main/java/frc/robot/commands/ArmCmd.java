package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCmd extends CommandBase {
	private final ArmSubsystem armSubsystem;
	private final XboxController controller;

	public ArmCmd(ArmSubsystem subsystem, XboxController controller) {
		this.armSubsystem = subsystem;
		this.controller = controller;
		addRequirements(this.armSubsystem);
	}

	@Override
	public void initialize() {}

	@Override
	public void execute() {
		this.armSubsystem.setDesiredState(controller.getLeftY() * Constants.Arm.ARM_SPEED);
	}

	@Override
	public void end(boolean interrupted) {
		this.armSubsystem.stopModules();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
