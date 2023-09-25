// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DriveMotorModule;
import frc.robot.subsystems.DriveMotorSubsystem;

public class Autos extends DriveMotorModule{

  public Autos(int Motor_Port, boolean reverse) {
    super(Motor_Port, reverse);
  }

}
