package frc.robot;

import frc.robot.auto.AutoFolderCube;
import frc.robot.auto.AutoMethod;
import frc.robot.commands.ArmCmd;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.subsystems.ArmBackwardSubsystem;
import frc.robot.subsystems.ArmForwardSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer {
	private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
	private final ArmForwardSubsystem armForwardSubsystem = new ArmForwardSubsystem();
	private final ArmBackwardSubsystem armBackwardSubsystem = new ArmBackwardSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final GamepadJoystick driverJoystick = new GamepadJoystick(GamepadJoystick.DRIVER_CONTROLLER_PORT);
	private final GamepadJoystick armJoystick = new GamepadJoystick(GamepadJoystick.ARM_CONTORLLER_PORT);
	private final DriveJoystickCmd driveCommand = new DriveJoystickCmd(driveMotorSubsystem, driverJoystick);
	private final IntakeCmd intakeCommand = new IntakeCmd(intakeSubsystem, armJoystick);
	private final ArmCmd armCommand = new ArmCmd(armForwardSubsystem, armBackwardSubsystem, armJoystick);
	private final AutoMethod autoMethod = new AutoMethod(driveMotorSubsystem, intakeSubsystem, armForwardSubsystem, armBackwardSubsystem);
	private final AutoFolderCube autoFolderCube = new AutoFolderCube(driveMotorSubsystem, intakeSubsystem, armForwardSubsystem, armBackwardSubsystem, autoMethod);

	public RobotContainer() {
		this.driveMotorSubsystem.setDefaultCommand(this.driveCommand);
		this.intakeSubsystem.setDefaultCommand(this.intakeCommand);
		this.armForwardSubsystem.setDefaultCommand(this.armCommand);
		this.armBackwardSubsystem.setDefaultCommand(this.armCommand);
	}

	private void autoBalance() {
		double pitchRate = Math.sin(this.driveMotorSubsystem.getPitch() * (Math.PI / 180.0)) + 0.08;
		this.driveMotorSubsystem.autoBalance(pitchRate);
		SmartDashboard.putNumber("GyroPitch", this.driveMotorSubsystem.getPitch());
		SmartDashboard.putNumber("PitchRate", pitchRate);
	}

	public Command getAutonomousCommand() {
		return new SequentialCommandGroup(
			new ParallelRaceGroup(
				Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
				new WaitCommand(0.7)
			),

			new WaitCommand(1.0),

			new ParallelRaceGroup(
				Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
				new WaitCommand(0.4)
			),

			new SequentialCommandGroup(
				new WaitCommand(1.0),
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
					new WaitCommand(0.4)
				),
				new WaitCommand(1.0)
			),

			new ParallelCommandGroup(
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
					new WaitCommand(0.15)
				),
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armBackwardDown, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
					new WaitCommand(0.3)		
				)
			),

			new WaitCommand(1.0),

			new ParallelCommandGroup(
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::intakePullCube, this.intakeSubsystem::stopModules, this.intakeSubsystem),
					new WaitCommand(2.0)
				),
				new SequentialCommandGroup(
					new WaitCommand(0.5),
					new ParallelRaceGroup(
						Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
						new WaitCommand(0.2)
					)
				)
			),

			new ParallelCommandGroup(
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
					new WaitCommand(1.5)	
				),
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armForwardDown, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
					new WaitCommand(0.8)
				)
			),

			new SequentialCommandGroup(
				// new ParallelRaceGroup(
				// 	Commands.runEnd(this.autoMethod::leftRotation, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
				// 	new WaitCommand(1.1)
				// ),
				new WaitCommand(1.0),
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
					new WaitCommand(0.5)
				)
			),

			new ParallelCommandGroup(
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
					new WaitCommand(1.0)
				),
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armForwardUP, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
					new WaitCommand(0.8)
				)	
			),

			new ParallelCommandGroup(
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::intakePushCube, this.intakeSubsystem::stopModules, this.intakeSubsystem),
					new WaitCommand(2.0)
				),
				new SequentialCommandGroup(
					new WaitCommand(1.0),
					new ParallelRaceGroup(
						Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
					new WaitCommand(0.4)
					)
				)
			),

			new ParallelCommandGroup(
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armForwardDown, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
					new WaitCommand(0.6)
				),
				new ParallelRaceGroup(
					Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
					new WaitCommand(0.8)
				)
			),

			Commands.run(this::autoBalance, this.driveMotorSubsystem)
		);
	}
}