package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class GamepadJoystick extends XboxController {    
    // COM ID
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int ARM_CONTORLLER_PORT = 1;
    private static final double DEADBAND = 0.05;

    public GamepadJoystick(final int port) {
        super(port);
    }

    public double getRobotDriveSpeed() {
        return MathUtil.applyDeadband(-this.getLeftY(), DEADBAND);
    }

    public double getRobotRotateSpeed() {
        return MathUtil.applyDeadband(-this.getRightX(), DEADBAND) * Constants.Drive.TURN_SPEED;
    }
}