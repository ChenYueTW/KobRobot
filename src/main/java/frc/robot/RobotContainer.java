package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.ElvatorButtonCmd;
import frc.robot.commands.IntakeDriveCmd;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.ElvatorMotorSubsystem;
import frc.robot.subsystems.IntakeMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import javax.naming.directory.DirContext;

import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {

  private final DriveMotorSubsystem DriverSubsystem = new DriveMotorSubsystem();
  private final ElvatorMotorSubsystem ElvatorSubsystem = new ElvatorMotorSubsystem(); 
  private final IntakeMotorSubsystem IntakeSubsystem = new IntakeMotorSubsystem();
  private final GamepadJoystick driverJoystick = new GamepadJoystick(GamepadJoystick.kDriverControllerPort);

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    DriverSubsystem.setDefaultCommand(new DriveJoystickCmd(DriverSubsystem, 
    () -> -driverJoystick.getRawAxis(GamepadJoystick.LDriverYAxis),
    () -> -driverJoystick.getRawAxis(GamepadJoystick.RDriverXAxis)));

    configureBindings();
  }

  private void configureBindings() {
    // Elvator
    driverJoystick.YButton.whileTrue(new ElvatorButtonCmd(ElvatorSubsystem, 0.3));
    driverJoystick.AButton.whileTrue(new ElvatorButtonCmd(ElvatorSubsystem, -0.3));
    // Intake
    driverJoystick.XButton.whileTrue(new IntakeDriveCmd(IntakeSubsystem, 0));
    driverJoystick.BButton.whileTrue(new IntakeDriveCmd(IntakeSubsystem, 0));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
