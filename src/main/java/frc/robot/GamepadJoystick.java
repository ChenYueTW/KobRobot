package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class GamepadJoystick extends Joystick{
    public GamepadJoystick(int port) {
        super(port);
    }
    
    //COM ID
    public static final int kDriverControllerPort = 0;
    
    // Axis ID
    public static final int LDriverXAxis = 0;
    public static final int LDriverYAxis = 1;
    public static final int RDriverXAxis = 4;
    public static final int RDriverYAxis = 5;

    public Trigger YButton = new JoystickButton(this, 0);
    public Trigger AButton = new JoystickButton(this, 1);
    public Trigger XButton = new JoystickButton(this, 3);
    public Trigger BButton = new JoystickButton(this, 2);
    public Trigger LeftButton = new JoystickButton(this, 4);
    public Trigger RightButton = new JoystickButton(this, 5);
}