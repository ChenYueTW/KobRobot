package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmBackwardSubsystem;
import frc.robot.subsystems.ArmForwardSubsystem;
import frc.robot.subsystems.DriveMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoFolderCone {
    private final DriveMotorSubsystem driveMotorSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final ArmForwardSubsystem armForwardSubsystem;
    private final ArmBackwardSubsystem armBackwardSubsystem;
    private final AutoMethod autoMethod;

    public AutoFolderCone(DriveMotorSubsystem driveMotorSubsystem, IntakeSubsystem intakeSubsystem, ArmForwardSubsystem armForwardSubsystem, ArmBackwardSubsystem armBackwardSubsystem, AutoMethod autoMethod) {
        this.driveMotorSubsystem = driveMotorSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.armForwardSubsystem = armForwardSubsystem;
        this.armBackwardSubsystem = armBackwardSubsystem;
        this.autoMethod = autoMethod;
    }

    public void stopModules() {
        this.driveMotorSubsystem.stopModules();
        this.intakeSubsystem.stopModules();
        this.armForwardSubsystem.stopModules();
        this.armBackwardSubsystem.stopModules();
    }

    public Command folderCone() {
        return new SequentialCommandGroup(
            new ParallelCommandGroup(
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
                    new WaitCommand(0.7)
                ),
                new SequentialCommandGroup(
                    new WaitCommand(0.2),
                    new ParallelRaceGroup(
                        Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
                        new WaitCommand(0.5)
                    )
                )
            ),

            new SequentialCommandGroup(
                new WaitCommand(0.5),
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
                    new WaitCommand(0.2)
                ),
                new WaitCommand(0.3)
            ),

            new ParallelRaceGroup(
                Commands.runEnd(this.autoMethod::armForwardDown, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
                new WaitCommand(0.7)
            ),

            new WaitCommand(1.0),

            new ParallelCommandGroup(
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::intakePullCone, this.intakeSubsystem::stopModules, this.intakeSubsystem),
                    new WaitCommand(2.0)
                ),
                new ParallelCommandGroup(
                    Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
                    new WaitCommand(0.4)
                )
            ),

            new ParallelCommandGroup(
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
                    new WaitCommand(0.5)
                ),
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::armForwardUP, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
                    new WaitCommand(0.3)
                )
            ),

            new SequentialCommandGroup(
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::leftRotation, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
                    new WaitCommand(1.1)
                ),
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::forwardDrive, this.driveMotorSubsystem::stopModules, this.driveMotorSubsystem),
                    new WaitCommand(1.0)
                )
            ),

            new ParallelCommandGroup(
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::armBackwardDown, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
                    new WaitCommand(0.3)
                ),
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::armForwardDown, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
                    new WaitCommand(0.4)
                )
            ),

            new ParallelCommandGroup(
                new ParallelRaceGroup(
                    Commands.runEnd(this.autoMethod::intakePushCone, this.intakeSubsystem::stopModules, this.intakeSubsystem),
                    new WaitCommand(2.0)
                ),
                new SequentialCommandGroup(
                    new WaitCommand(0.2),
                    new ParallelCommandGroup(
                        new SequentialCommandGroup(
                            new WaitCommand(0.2),
                            Commands.runEnd(this.autoMethod::armBackwardUP, this.armBackwardSubsystem::stopModules, this.armBackwardSubsystem),
                            new WaitCommand(0.5)
                        ),
                        new ParallelRaceGroup(
                            Commands.runEnd(this.autoMethod::armForwardUP, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
                            new WaitCommand(0.2)
                        )
                    )
                )
            ),

            new ParallelRaceGroup(
                Commands.runEnd(this.autoMethod::armForwardUP, this.armForwardSubsystem::stopModules, this.armForwardSubsystem),
                new WaitCommand(0.3)
            )
        );
    }
}
