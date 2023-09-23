package frc.robot;

public class robotMap {
    public class DriverPort {
        public class PWM_Port {
            public static final int kL1MotorPort = 1;
            public static final int kL2MotorPort = 2;
            public static final int kR1MotorPort = 3;
            public static final int kR2MotorPort = 4;
        }

        public class Elvator_Port {
            public static final int Elvator1MotorPort = 3;
            public static final int Elvator2MotorPort = 4;
        }

        public class Arm_Port {
            public static final int Arm1MotorPort = 2;
            public static final int Arm2MotorPort = 3;
        }

        public class Intake_Port {
            public static final int IntakeMotorPort = 5;
        }
    }
}