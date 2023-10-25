package frc.robot;

import frc.robot.commands.ArmCmd;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
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
	private final GamepadJoystick armJoystick = new GamepadJoystick(GamepadJoystick.ARM_CONTORLLER_PORT);
	private final AutoMethod autoMethod = new AutoMethod();
	private final DriveJoystickCmd driveCommand = new DriveJoystickCmd(driveMotorSubsystem, driverJoystick);
	private final IntakeCmd intakeCommand = new IntakeCmd(intakeSubsystem, armJoystick);
	private final ArmCmd armCommand = new ArmCmd(armSubsystem, armJoystick);
	private final AddressableLED led = new AddressableLED(LedPin.led);

	public RobotContainer() {
		this.driveMotorSubsystem.setDefaultCommand(this.driveCommand);
		this.intakeSubsystem.setDefaultCommand(this.intakeCommand);
		this.armSubsystem.setDefaultCommand(this.armCommand);
		this.configLed();
	}

	private void autoBalance() {
		double pitchRate = Math.sin(this.driveMotorSubsystem.getPitch() * (Math.PI / 180.0)) * -1;
		this.driveMotorSubsystem.autoBalance(pitchRate);
		SmartDashboard.putNumber("GyroPitch", this.driveMotorSubsystem.getPitch());
		SmartDashboard.putNumber("PitchRate", pitchRate);
	}

	private void configLed() {
		AddressableLEDBuffer buffer = new AddressableLEDBuffer(15);
		this.led.setLength(buffer.getLength());

		for (int i = 0; i <= buffer.getLength(); i++) {
			buffer.setRGB(i, 255, 0, 0);
		}
		for (int i = buffer.getLength(); i <= 0; i --) {
			buffer.setRGB(i, 0, 255, 0);
		}

		this.led.setData(buffer);
		this.led.start();
	}

	public Command getAutonomousCommand() {
		return new SequentialCommandGroup(
			new SequentialCommandGroup(
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::backwardDrvie, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::leftRotation, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
				new WaitCommand(1.0),
				new ParallelRaceGroup(Commands.runEnd(this.autoMethod::rightRotation, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
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
			Commands.runEnd(this::autoBalance, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem)
		);
	}
}
