package frc.robot;

import frc.robot.commands.ArmCmd;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.subsystems.ArmSubsystem;
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
	private final ArmSubsystem armSubsystem = new ArmSubsystem();
	private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	private final GamepadJoystick driverJoystick = new GamepadJoystick(GamepadJoystick.DRIVER_CONTROLLER_PORT);
	private final GamepadJoystick armJoystick = new GamepadJoystick(GamepadJoystick.ARM_CONTORLLER_PORT);
	private final AutoMethod autoMethod = new AutoMethod(driveMotorSubsystem, intakeSubsystem ,armSubsystem);
	private final DriveJoystickCmd driveCommand = new DriveJoystickCmd(driveMotorSubsystem, driverJoystick);
	private final IntakeCmd intakeCommand = new IntakeCmd(intakeSubsystem, armJoystick);
	private final ArmCmd armCommand = new ArmCmd(armSubsystem, armJoystick);

	public RobotContainer() {
		this.driveMotorSubsystem.setDefaultCommand(this.driveCommand);
		this.intakeSubsystem.setDefaultCommand(this.intakeCommand);
		this.armSubsystem.setDefaultCommand(this.armCommand);
	}

	private void autoBalance() {
		double pitchRate = Math.sin(this.driveMotorSubsystem.getPitch() * (Math.PI / 180.0)) * -1;
		// this.driveMotorSubsystem.autoBalance(pitchRate);
		SmartDashboard.putNumber("GyroPitch", this.driveMotorSubsystem.getPitch());
		SmartDashboard.putNumber("PitchRate", pitchRate);
	}

	public Command getAutonomousCommand() {
		return new SequentialCommandGroup(
					new ParallelRaceGroup(
						Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
						new WaitCommand(2.0)
					),

					new ParallelRaceGroup(
						new ParallelCommandGroup(
							Commands.runEnd(this.autoMethod::armBackwardDown, this.armSubsystem::stopBackwardModule, this.armSubsystem),
							Commands.runEnd(this.autoMethod::armForwardUP, this.armSubsystem::stopForwardModule, this.armSubsystem)
						),
						new WaitCommand(3.0)
					),
		
					new ParallelCommandGroup(
						new ParallelRaceGroup(
							Commands.runEnd(this.autoMethod::intakePullCube, this.intakeSubsystem::stopModules, this.armSubsystem),
							new WaitCommand(2.0)
						),
						new ParallelRaceGroup(
							Commands.runEnd(this.autoMethod::armForwardDown, this.armSubsystem::stopForwardModule, this.armSubsystem),
							new WaitCommand(1.5)
						)	
					),

					new ParallelRaceGroup(
						Commands.runEnd(this.autoMethod::armBackwardUP, this.armSubsystem::stopBackwardModule, this.armSubsystem),
						new WaitCommand(2.0)	
					),

					new SequentialCommandGroup(
						new ParallelRaceGroup(
							Commands.runEnd(this.autoMethod::leftRotation, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
							new WaitCommand(1.8)
						),
						new ParallelRaceGroup(
							Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
							new WaitCommand(2.0)
						)
					),

					new ParallelRaceGroup(
						Commands.runEnd(this.autoMethod::armBackwardDown, this.armSubsystem::stopBackwardModule, this.armSubsystem),
						new WaitCommand(2.0)
					),

					new ParallelRaceGroup(
						Commands.runEnd(this.autoMethod::intakePullCube, this.intakeSubsystem::stopModules, this.intakeSubsystem),
						new WaitCommand(2.0)
					),

					Commands.run(this::autoBalance, this.driveMotorSubsystem)
		);	
	}
}