/* Team 5687 (C)2020-2021 */
package org.frc5687.swerve;

public class RobotMap {

    /**
     * There should be an entry here for each CAN device, preferrably grouped by device type and
     * then in numerical order. Note that for CAN, ids must be unique per device type, but not
     * across types. Thus, you cannot have two SparkMax controllers with Id 0, but you can have a
     * SparkMax with Id 0 and a TalonSRX with Id 0.
     */
    public static class CAN {

        public static class TALONFX {
            public static final int NORTH_EAST_OUTER = 7;
            public static final int NORTH_EAST_INNER = 8;
            public static final int SOUTH_EAST_INNER = 5;
            public static final int SOUTH_EAST_OUTER = 6;
            public static final int SOUTH_WEST_INNER = 4;
            public static final int SOUTH_WEST_OUTER = 3;
            public static final int NORTH_WEST_INNER = 2;
            public static final int NORTH_WEST_OUTER = 1;

            public static final int INDEXER = 11;
            public static final int SHOOTER = 13;
            public static final int INTAKE = 12;
        }
    }

    /**
     * There should be an entry here for each PWM port, preferrably in numerical order. Note that
     * for PWM only one device can connect to each port, so the numbers should be unique.
     */
    public static class PWM {}

    /**
     * There should be an entry here for each PCM port, preferrably in numerical order. Note that
     * for PCM only one device can connect to each port, so the numbers should be unique.
     */
    public static class PCM {}

    /**
     * There should be an entry here for each PDP breaker, preferrably in numerical order. Note that
     * only on device can be connected to each breaker, so the numbers should be unique.
     */
    public static class PDP {}

    /**
     * There should be an entry here for each Analgo port, preferrably in numerical order. Note that
     * for Analog only one device can connect to each port, so the numbers should be unique.
     */
    public static class Analog {}

    /**
     * There should be an entry here for each DIO port, preferrably in numerical order. Note that
     * for DIO only one device can connect to each port, so the numbers should be unique.
     */
    public static class DIO {
        public static final int SOUTH_EAST = 1;
        public static final int SOUTH_WEST = 2;
        public static final int NORTH_WEST = 3;
        public static final int NORTH_EAST = 4;
        public static final int INTAKE_BEAM_BREAK = 5;
        public static final int INDEXER_BEAM_BREAK = 6;
    }
}