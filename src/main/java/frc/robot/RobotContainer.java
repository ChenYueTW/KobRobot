package frc.robot;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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
    this.driveMotorSubsystem.setDefaultCommand(Commands.run(this::driveRobot, this.driveMotorSubsystem));
    this.elevatorSubsystem.setDefaultCommand(Commands.run(this::runElevatorDefaultCommand, this.elevatorSubsystem));
    this.armSubsystem.setDefaultCommand(Commands.run(this::runArmDefaultCommand, this.armSubsystem));
    this.intakeSubsystem.setDefaultCommand(Commands.run(this::runIntakeDefaultCommand, this.intakeSubsystem));
  }

  private void driveRobot() {
    double driveSpeed = this.driverJoystick.getRobotDriveSpeed();
    double turnSpeed = this.driverJoystick.getRobotRotateSpeed();
    double leftDriveSpeed = driveSpeed - turnSpeed;
    double rightDriveSpeed = driveSpeed + turnSpeed;
    this.driveMotorSubsystem.driverMove(leftDriveSpeed, rightDriveSpeed);
    SmartDashboard.putNumber("Left-Speed", leftDriveSpeed);
    SmartDashboard.putNumber("Right-Speed", rightDriveSpeed);
  }

  private void runElevatorDefaultCommand() {
    if (this.driverJoystick.getYButton()) {
      this.elevatorSubsystem.elevate(true);
    } else if (this.driverJoystick.getAButton()) {
      this.elevatorSubsystem.elevate(false);
    } else {
      this.elevatorSubsystem.stopModules();
    }
  }

  private void runArmDefaultCommand() {
    if (this.driverJoystick.getLeftBumper()) {
      this.armSubsystem.arm(true);
    } else if (this.driverJoystick.getRightBumper()) {
      this.armSubsystem.arm(false);
    } else {
      this.armSubsystem.stopModules();
    }
  }

  private void runIntakeDefaultCommand(){
    if (this.driverJoystick.getXButton()){
      this.intakeSubsystem.intake(true);
    } else if(this.driverJoystick.getBButton()){
      this.intakeSubsystem.intake(false);
    } else{
      this.intakeSubsystem.stopModules();
    }
  }

  private void autoDriveCommand(){
    this.driveMotorSubsystem.autoDriveSystem(true);
  }

  private void autoDriveCommandForward(){
    this.driveMotorSubsystem.autoDriveSystem(false);
  }

  private void autoTurnLeftDriveCommand(){
    this.driveMotorSubsystem.autoTrunDriveSystem(false);
  }

  private void autoTurnRightDriveCommand(){
    this.driveMotorSubsystem.autoTrunDriveSystem(true);
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new ParallelRaceGroup(Commands.runEnd(this::autoDriveCommand, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
      new WaitCommand(1.0),
      new ParallelRaceGroup(Commands.runEnd(this::autoDriveCommandForward, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(1.0)),
      new WaitCommand(1.0),

      new ParallelRaceGroup(Commands.runEnd(this::autoTurnLeftDriveCommand, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(2.0)),
      new WaitCommand(1.0),
      new ParallelRaceGroup(Commands.runEnd(this::autoTurnRightDriveCommand, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem), new WaitCommand(2.0))
    );
  }
}
