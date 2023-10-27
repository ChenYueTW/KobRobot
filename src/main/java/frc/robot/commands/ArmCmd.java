package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
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
		double armSpeedForward = MathUtil.applyDeadband(this.controller.getLeftY(), Constants.DEAD_BAND);
		double armSpeedBackward = MathUtil.applyDeadband(this.controller.getRightY(), Constants.DEAD_BAND);
		double brakesForward = 1 - MathUtil.applyDeadband(this.controller.getLeftTriggerAxis(), Constants.DEAD_BAND);
		double brakesBackward = 1 - MathUtil.applyDeadband(this.controller.getRightTriggerAxis(), Constants.DEAD_BAND);

		this.armSubsystem.setDesiredStateForward(armSpeedForward * brakesForward * Constants.Control.ARM_FORWARD_SPEED);
		this.armSubsystem.setDesiredStateBackward(armSpeedBackward * brakesBackward * Constants.Control.ARM_BACKWARD_SPEED);
	}

	@Override
	public void end(boolean interrupted) {
		this.armSubsystem.stopForwardModule();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
