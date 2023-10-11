package frc.robot;

import frc.robot.commands.ArmDriveCmd;
import frc.robot.commands.AutoDriveCmd;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.ElevatorDriveCmd;
import frc.robot.commands.IntakeDriveCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer {
  private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(); 
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final GamepadJoystick driverJoystick = new GamepadJoystick(GamepadJoystick.Controller_Port);

  public RobotContainer() {
    this.driveMotorSubsystem.setDefaultCommand(new DriveJoystickCmd(this.driveMotorSubsystem, 
    () -> -this.driverJoystick.getRawAxis(GamepadJoystick.left_Y_Axis),
    () -> -this.driverJoystick.getRawAxis(GamepadJoystick.right_X_Axis)));
    this.configureBindings();
  }

  private void configureBindings() {
    // Elevator
    driverJoystick.buttonY.whileTrue(new ElevatorDriveCmd(elevatorSubsystem, 0.3));
    driverJoystick.buttonA.whileTrue(new ElevatorDriveCmd(elevatorSubsystem, -0.3));
    // Intake
    driverJoystick.buttonX.whileTrue(new IntakeDriveCmd(intakeSubsystem, 0.2));
    driverJoystick.buttonB.whileTrue(new IntakeDriveCmd(intakeSubsystem, -0.2));
    // Arm
    driverJoystick.buttonLeft.whileTrue(new ArmDriveCmd(armSubsystem, 0.3));
    driverJoystick.buttonRight.whileTrue(new ArmDriveCmd(armSubsystem, -0.3));
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
    new ParallelRaceGroup(new AutoDriveCmd(this.driveMotorSubsystem, 0.3), new WaitCommand(1.0)));
  }
}
