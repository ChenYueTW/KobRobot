package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class GamepadJoystick extends XboxController {    
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int ARM_CONTORLLER_PORT = 1;

    public GamepadJoystick(final int port) {
        super(port);
    }
}