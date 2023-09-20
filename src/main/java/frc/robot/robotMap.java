package frc.robot;

import edu.wpi.first.networktables.PubSub;

public class robotMap {
    public class DriverPort {
        public class PWM_Port {
            public static final int kL1MotorPort = 1;
            public static final int kL2MotorPort = 2;
            public static final int kR1MotorPort = 3;
            public static final int kR2MotorPort = 4;
        }

        public class CAN_Port {
            public static final int Elevator1MotorPort = 3;
        }
    }
}