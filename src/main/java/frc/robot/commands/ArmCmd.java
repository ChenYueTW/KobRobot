package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmBackwardSubsystem;
import frc.robot.subsystems.ArmForwardSubsystem;

public class ArmCmd extends CommandBase {
	private final ArmForwardSubsystem armForwardSubsystem;
	private final ArmBackwardSubsystem armBackwardSubsystem;
	private final XboxController controller;

	public ArmCmd(ArmForwardSubsystem forwardSubsystem, ArmBackwardSubsystem backwardSubsystem, XboxController controller) {
		this.armForwardSubsystem = forwardSubsystem;
		this.armBackwardSubsystem = backwardSubsystem;
		this.controller = controller;
		addRequirements(this.armForwardSubsystem, this.armBackwardSubsystem);
	}

	@Override
	public void initialize() {}

	@Override
	public void execute() {
		double armSpeedForward = MathUtil.applyDeadband(this.controller.getLeftY(), Constants.DEAD_BAND);
		double armSpeedBackward = MathUtil.applyDeadband(this.controller.getRightY(), Constants.DEAD_BAND);
		double brakesForward = 1 - MathUtil.applyDeadband(this.controller.getLeftTriggerAxis(), Constants.DEAD_BAND);
		double brakesBackward = 1 - MathUtil.applyDeadband(this.controller.getRightTriggerAxis(), Constants.DEAD_BAND);

		this.armForwardSubsystem.setDesiredStateForward(armSpeedForward * brakesForward * Constants.Control.ARM_FORWARD_SPEED);
		this.armBackwardSubsystem.setDesiredStateBackward(armSpeedBackward * brakesBackward * Constants.Control.ARM_BACKWARD_SPEED);
		// if(this.controller.getYButton()) {
		// 	this.armBackwardSubsystem.resetEncoder();
		// }
	}

	@Override
	public void end(boolean interrupted) {
		this.armForwardSubsystem.stopModules();
		this.armBackwardSubsystem.stopModules();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
