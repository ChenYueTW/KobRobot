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

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer {
  private final DriveMotorSubsystem driveMotorSubsystem = new DriveMotorSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(); 
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final GamepadJoystick driverJoystick = new GamepadJoystick(GamepadJoystick.CONTROLLER_PORT);

  public RobotContainer() {
    this.driveMotorSubsystem.setDefaultCommand(new DriveJoystickCmd(this.driveMotorSubsystem, 
    () -> -this.driverJoystick.getRawAxis(GamepadJoystick.left_Y_Axis),
    () -> -this.driverJoystick.getRawAxis(GamepadJoystick.right_X_Axis)));
    this.configureBindings();
  }

  private void configureBindings() {
    // Elevator
    this.driverJoystick.buttonY.whileTrue(new ElevatorDriveCmd(this.elevatorSubsystem, true));
    this.driverJoystick.buttonA.whileTrue(new ElevatorDriveCmd(this.elevatorSubsystem, false));
    // Intake
    this.driverJoystick.buttonX.whileTrue(new IntakeDriveCmd(this.intakeSubsystem, true));
    this.driverJoystick.buttonB.whileTrue(new IntakeDriveCmd(this.intakeSubsystem, false));
    // Arm
    this.driverJoystick.buttonLeft.whileTrue(new ArmDriveCmd(this.armSubsystem, true));
    this.driverJoystick.buttonRight.whileTrue(new ArmDriveCmd(this.armSubsystem, false));
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new ParallelRaceGroup(new AutoDriveCmd(this.driveMotorSubsystem, true), new WaitCommand(1.0)));
  }
}
