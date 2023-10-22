package frc.robot;

import frc.robot.auto.AutoBalance;
import frc.robot.auto.AutoMethod;
import frc.robot.commands.ArmCmd;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer {
	private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
	private final ArmSubsystem armSubsystem = new ArmSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final GamepadJoystick driverJoystick = new GamepadJoystick(GamepadJoystick.DRIVER_CONTROLLER_PORT);
	private final GamepadJoystick takeJoystick = new GamepadJoystick(GamepadJoystick.ARM_CONTORLLER_PORT);
	private final AutoMethod autoMethod = new AutoMethod();
	private final DriveJoystickCmd driveCommand = new DriveJoystickCmd(driveMotorSubsystem, driverJoystick);
	private final IntakeCmd intakeCommand = new IntakeCmd(intakeSubsystem, takeJoystick);
	private final ArmCmd armCommand = new ArmCmd(armSubsystem, takeJoystick);

	public RobotContainer() {
		this.driveMotorSubsystem.setDefaultCommand(driveCommand);
		this.intakeSubsystem.setDefaultCommand(intakeCommand);
		this.armSubsystem.setDefaultCommand(armCommand);
	}

	public void onRobotPeriodic() {
		SmartDashboard.putNumber("Gyro-Pitch", this.driveMotorSubsystem.getPitch());
	}

	public Command getAutonomousCommand() {
		return new SequentialCommandGroup(
			new SequentialCommandGroup(
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::backwardDrvie, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::turnLeftDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::turnRightDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0)
			),
			new SequentialCommandGroup(
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::pullSystem, this.intakeSubsystem::stopModules, this.intakeSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::pushSystem, this.intakeSubsystem::stopModules, this.intakeSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0)
			),
			new SequentialCommandGroup(
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::armUp, this.armSubsystem::stopModules, this.armSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::armDown, this.armSubsystem::stopModules, this.armSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0)
			),
			new AutoBalance(driveMotorSubsystem)
		);
	}
}
