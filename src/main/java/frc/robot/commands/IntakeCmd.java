package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends CommandBase {
	private final IntakeSubsystem intakeSubsystem;
	private final XboxController controller;

	public IntakeCmd(IntakeSubsystem subsystem, XboxController controller) {
		this.intakeSubsystem = subsystem;
		this.controller = controller;
		addRequirements(this.intakeSubsystem);
	}

	@Override
	public void initialize() {}

	@Override
	public void execute() {
		if(this.controller.getXButton()){
			this.intakeSubsystem.setDesiredState(Constants.Control.INTAKE_SPEED);
		} else if(this.controller.getBButton()) {
			this.intakeSubsystem.setDesiredState(-Constants.Control.INTAKE_SPEED);
		} else if(this.controller.getLeftBumper()) {
			this.intakeSubsystem.setDesiredState(-Constants.Control.INTAKE_SPEED);
		} else if(this.controller.getRightBumper()) {
			this.intakeSubsystem.setDesiredState(Constants.Control.INTAKE_SPEED);
		} else {
			this.intakeSubsystem.stopModules();
		}
	}

	@Override
	public void end(boolean interrupted) {
		this.intakeSubsystem.stopModules();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
