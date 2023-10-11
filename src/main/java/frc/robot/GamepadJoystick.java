package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class GamepadJoystick extends Joystick{
    public GamepadJoystick(int port) {
        super(port);
    }
    
    // COM ID
    public static final int CONTROLLER_PORT = 0;
    
    // Axis ID
    public static final int left_X_Axis = 0;
    public static final int left_Y_Axis = 1;
    public static final int right_X_Axis = 4;
    public static final int right_Y_Axis = 5;

    // Button ID
    public final Trigger buttonY = new JoystickButton(this, 4);
    public final Trigger buttonA = new JoystickButton(this, 1);
    public final Trigger buttonX = new JoystickButton(this, 3);
    public final Trigger buttonB = new JoystickButton(this, 2);
    public final Trigger buttonLeft = new JoystickButton(this, 5);
    public final Trigger buttonRight = new JoystickButton(this, 6);
}