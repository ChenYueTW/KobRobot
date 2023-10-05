package frc.robot;

import frc.robot.commands.ArmDriveCmd;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.ElvatorDriveCmd;
import frc.robot.commands.IntakeDriveCmd;
import frc.robot.subsystems.ArmMotorSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.ElvatorMotorSubsystem;
import frc.robot.subsystems.IntakeMotorSubsystem;
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
  private final DriveMotorSubsystem DriverSubsystem = new DriveMotorSubsystem();
  private final ElvatorMotorSubsystem ElvatorSubsystem = new ElvatorMotorSubsystem(); 
  private final ArmMotorSubsystem ArmSubsystem = new ArmMotorSubsystem();
  private final IntakeMotorSubsystem IntakeSubsystem = new IntakeMotorSubsystem();
  private final GamepadJoystick DriverJoystick = new GamepadJoystick(GamepadJoystick.kDriverControllerPort);

  public RobotContainer() {
    DriverSubsystem.setDefaultCommand(new DriveJoystickCmd(DriverSubsystem, 
    () -> -DriverJoystick.getRawAxis(GamepadJoystick.LDriverYAxis),
    () -> -DriverJoystick.getRawAxis(GamepadJoystick.RDriverXAxis)));

    configureBindings();
  }

  private void configureBindings() {
    // Elvator
    DriverJoystick.YButton.whileTrue(new ElvatorDriveCmd(ElvatorSubsystem, 0.3));
    DriverJoystick.AButton.whileTrue(new ElvatorDriveCmd(ElvatorSubsystem, -0.3));
    // Intake
    DriverJoystick.XButton.whileTrue(new IntakeDriveCmd(IntakeSubsystem, 0.3));
    DriverJoystick.BButton.whileTrue(new IntakeDriveCmd(IntakeSubsystem, -0.3));
    // Arm
    DriverJoystick.LeftButton.whileTrue(new ArmDriveCmd(ArmSubsystem, 0.3));
    DriverJoystick.RightButton.whileTrue(new ArmDriveCmd(ArmSubsystem, -0.3));
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
    new ParallelRaceGroup(new Autos(DriverSubsystem), new WaitCommand(1)));
  }
}
