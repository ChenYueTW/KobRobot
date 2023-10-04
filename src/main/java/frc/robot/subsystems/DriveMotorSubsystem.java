package frc.robot.subsystems;

import frc.robot.robotMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveMotorSubsystem extends SubsystemBase { 
    private final DriveMotorModule kL1_Motor;
    private final DriveMotorModule kL2_Motor;
    private final DriveMotorModule kR1_Motor;
    private final DriveMotorModule kR2_Motor;

    public DriveMotorSubsystem() {
        kL1_Motor = new DriveMotorModule(robotMap.DriverPort.PWM_Port.kL1MotorPort, false);
        kL2_Motor = new DriveMotorModule(robotMap.DriverPort.PWM_Port.kL2MotorPort, false);
        kR1_Motor = new DriveMotorModule(robotMap.DriverPort.PWM_Port.kR1MotorPort, true);
        kR2_Motor = new DriveMotorModule(robotMap.DriverPort.PWM_Port.kR2MotorPort, true);
    }

    public void kDrivermove(Double LeftkMotorSpeed, Double RightkMotorSpeed) {
        kL1_Motor.setDesiredState(LeftkMotorSpeed);
        kL2_Motor.setDesiredState(LeftkMotorSpeed);
        kR1_Motor.setDesiredState(RightkMotorSpeed);
        kR2_Motor.setDesiredState(RightkMotorSpeed);
    }

    public void stopModules() {
        kL1_Motor.stop();
        kL2_Motor.stop();
        kR1_Motor.stop();
        kR2_Motor.stop();
    }
}