// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.ElevatorButtonCmd;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.ElevatorMotorSubsystem;

public class RobotContainer {

  private final ElevatorMotorSubsystem ElevatorSubsystem = new ElevatorMotorSubsystem(); 
  private final DriveMotorSubsystem DriverSubsystem = new DriveMotorSubsystem();
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
    driverJoystick.YButton.whileTrue(new ElevatorButtonCmd(ElevatorSubsystem, 0.3));
    driverJoystick.AButton.whileTrue(new ElevatorButtonCmd(ElevatorSubsystem, -0.3));
  }

  public Command getAutonomousCommand() {
    // Autos.exampleAuto(m_exampleSubsystem);
    return null;
  }
}
