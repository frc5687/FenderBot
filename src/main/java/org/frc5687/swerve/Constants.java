/* Team 5687 (C)2020-2022 */
package org.frc5687.swerve;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final int TICKS_PER_UPDATE = 1;
    public static final double METRIC_FLUSH_PERIOD = 1.0;
    public static final double UPDATE_PERIOD = 0.02;
    public static final double EPSILON = 0.00001;

    public static class DriveTrain {

        // Control
        public static final double DEADBAND = 0.2; // Avoid unintentional joystick movement


        // Size of the robot chassis in meters
        public static final double WIDTH = 0.6223; // meters
        public static final double LENGTH = 0.6223; // meters

        /**
         * Swerve modules are on four corners of robot:
         *
         * NW  <- Width of robot ->  NE
         *             / \
         *              |
         *        Length of robot
         *              |
         *             \ /
         *  SW                       SE
         */

        // Distance of swerve modules from center of robot
        public static final double SWERVE_NS_POS = LENGTH / 2.0;
        public static final double SWERVE_WE_POS = WIDTH / 2.0;

        /**
         *
         * Coordinate system is wacky:
         *
         * (X, Y):
         *   X is N or S, N is +
         *   Y is W or E, W is +
         *
         *   NW (+,+)  NE (+,-)
         *
         *   SW (-,+)  SE (-,-)
         *
         * We go counter-counter clockwise starting at NW of chassis:
         *
         *  NW, SW, SE, NE
         *
         * Note: when robot is flipped over, this is clockwise.
         *
         */

        // Position vectors for the swerve module kinematics
        // i.e. location of each swerve module from center of robot
        // see coordinate system above to understand signs of vector coordinates
        public static final Translation2d NORTH_WEST = new Translation2d( SWERVE_NS_POS, SWERVE_WE_POS ); // +,+

        public static final Translation2d NORTH_EAST = new Translation2d( SWERVE_NS_POS, -SWERVE_WE_POS ); // +,-
        public static final Translation2d SOUTH_WEST = new Translation2d( -SWERVE_NS_POS, SWERVE_WE_POS ); // -,+
        public static final Translation2d SOUTH_EAST = new Translation2d( -SWERVE_NS_POS, -SWERVE_WE_POS ); // -,-

        // Should be 0, but can correct for hardware error in swerve module headings here.
        public static final double NORTH_WEST_OFFSET = 0; // radians
        public static final double SOUTH_WEST_OFFSET = 0; // radians
        public static final double SOUTH_EAST_OFFSET = 0; // radians
        public static final double NORTH_EAST_OFFSET = 0; // radians

        // In case encoder is measuring rotation in the opposite direction we expect.
        public static final boolean NORTH_WEST_ENCODER_INVERTED = false;
        public static final boolean SOUTH_WEST_ENCODER_INVERTED = false;
        public static final boolean SOUTH_EAST_ENCODER_INVERTED = false;
        public static final boolean NORTH_EAST_ENCODER_INVERTED = false;

        // Maximum rates of motion
        public static final double MAX_MPS = 2.5; // Max speed of robot (m/s)
        public static final double MAX_MPS_TURBO = 3.8; // Max speed of robot in turbo (m/s)
        public static final double MAX_MPS_DURING_CLIMB = MAX_MPS / 4; // Max speed of robot (m/s) during climb
        public static final double MAX_ANG_VEL = Math.PI * 1.5; // Max rotation rate of robot (rads/s)
        public static final double MAX_ANG_VEL_AIM = 2 * Math.PI; // Max rotation rate of robot (rads/s)
        public static final double MAX_MPSS = 2.5; // Max acceleration of robot (m/s^2)

        // PID controller settings
        public static final double ANGLE_kP = 0.0; // 2.3
        public static final double ANGLE_kI = 0.0;
        public static final double ANGLE_kD = 0.0; // 0.6
        public static final double PROFILE_CONSTRAINT_VEL = MAX_ANG_VEL;
        public static final double PROFILE_CONSTRAINT_ACCEL = Math.PI * 3.0;

        public static final double kP = 3.5;
        public static final double kI = 0.0;
        public static final double kD = 0.1;

        // Vision PID controller
        public static final double VISION_TOLERANCE = 0.04; // rads
        public static final double VISION_kP = 5.6;
        public static final double VISION_kI = 0.0;
        public static final double VISION_kD = 0.2;
        public static final double VISION_IRANGE = MAX_MPS * 2;
        public static final double MAX_ANG_VEL_VISION = Math.PI * 3; // Max rotation rate of robot (rads/s)
        public static final long VISION_LATENCY = 50;

        public static final double BALL_VISION_TOLERANCE = 0.040; // rads
        public static final double BALL_VISION_kP = 3.1;
        public static final double BALL_VISION_kI = 0.0;
        public static final double BALL_VISION_kD = 0.2;
        public static final double BALL_VISION_IRANGE = MAX_MPS * 2;

        public static final double POSITION_TOLERANCE = 0.03;
        public static final double ANGLE_TOLERANCE = 0.02;
        public static final double ROTATING_TOLERANCE = 0.3;
    }

    public static class DifferentialSwerveModule {

        // update rate of our modules 5ms.
        public static final double kDt = 0.005;

        public static final double FALCON_FREE_SPEED =
                Units.rotationsPerMinuteToRadiansPerSecond(6380);
        public static final int TIMEOUT = 200;
        public static final double GEAR_RATIO_WHEEL = 6.46875 / 1.2;
        public static final double GEAR_RATIO_STEER = 9.2 / 1.2;
        public static final double FALCON_RATE = 600.0;
        public static final double WHEEL_RADIUS = 0.0508; // Meters with compression.
        public static final double MAX_MODULE_SPEED_MPS =
                (FALCON_FREE_SPEED / GEAR_RATIO_WHEEL) * WHEEL_RADIUS;
        public static final double TICKS_TO_ROTATIONS = 2048.0;
        public static final double VOLTAGE = 12.0;
        public static final double FEED_FORWARD = VOLTAGE / (FALCON_FREE_SPEED / GEAR_RATIO_WHEEL);

        public static final boolean ENABLE_CURRENT_LIMIT = true;
        public static final double CURRENT_LIMIT = 30.0;
        public static final double CURRENT_THRESHOLD = 30.0;
        public static final double CURRENT_TRIGGER_TIME = 0.0;

        // Create Parameters for DiffSwerve State Space
        public static final double INERTIA_WHEEL = 0.005;
        public static final double INERTIA_STEER = 0.004;
        // A weight for how aggressive each state should be ie. 0.08 radians will try to control the
        // angle more aggressively than the wheel angular velocity.
        public static final double Q_AZIMUTH_ANG_VELOCITY = 1.1; // radians per sec
        public static final double Q_AZIMUTH = 0.08; // radians
        public static final double Q_WHEEL_ANG_VELOCITY = 5; // radians per sec
        // This is for Kalman filter which isn't used for azimuth angle due to angle wrapping.
        // Model noise are assuming that our model isn't as accurate as our sensors.
        public static final double MODEL_AZIMUTH_ANGLE_NOISE = .1; // radians
        public static final double MODEL_AZIMUTH_ANG_VELOCITY_NOISE = 5.0; // radians per sec
        public static final double MODEL_WHEEL_ANG_VELOCITY_NOISE = 5.0; // radians per sec
        // Noise from sensors. Falcon With Gearbox causes us to have more uncertainty so we increase
        // the noise.
        public static final double SENSOR_AZIMUTH_ANGLE_NOISE = 0.01; // radians
        public static final double SENSOR_AZIMUTH_ANG_VELOCITY_NOISE = 0.1; // radians per sec
        public static final double SENSOR_WHEEL_ANG_VELOCITY_NOISE = 0.1; // radians per sec
        public static final double CONTROL_EFFORT = VOLTAGE;
    }

    public static class INDEXER{
        public static final double INDEXING_SPEED = -0.85;
        public static final double IDLE_INDEXER = 0;
        public static final double INTAKING_SPEED = -0.25;
        public static final double CLEANING_SPEED = 0.85;
        public static final double TICKS_TO_ROTATIONS = 2048.0;
        public static final double GEAR_RATIO = 5;
        public static final long EDGE_IN_DELAY = 1000;
    }

    public static class SHOOTER{
        public static final double SHOOTING_SPEED = 0.43;
        public static final double IDLE_SHOOTING_SPEED = 0.20;
        public static final double CLEANING_SPEED = -0.43;
        public static final boolean INVERTED = false;
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;

        
        public static final double TICKS_TO_ROTATIONS = 2048.0;
        public static final double GEAR_RATIO = 1.3125;
    }

    public static class INTAKE{
        public static final double INTAKEING_SPEED = 0.90;
        public static final double RETRACTING_SPEED = 0.00;
        public static final double IDLE_INTAKEING_SPEED = 0.00; //-0.30;
        public static final double CLEANING_SPEED = 0.00;
        public static final long RETRACT_DELAY = 2000;
        public static final boolean INVERTED = false;
    }

    public static class Maverick{
        //Constants for controling Maverick
        public static short TOTAL_NUMB_OF_WAYPOINTS = 2; // Dropped down from four so Maverick doesn't overrun the position
        public static double[] waypointsX = {1.2, 0.0, 0.0, 0.0};
        public static double[] waypointsY = {-1.2, 0.0, 0.0, 0.0};
        public static double[] rotations = {-0.10, -0.10, -0.10, 0.0};
        public static double[] tolerences = {0.0, 0.0, 0.0, 0.0};
        public static double[] speeds = {1.5, 1.5, 1.5, 3.5, 3.5};
        public static boolean[] afterburner = {false, false, false, false};

        public static double AUTO_X = 1.0;
        public static double AUTO_Y = 0.0;
        public static double AUTO_THETA = 0.0;
        public static double AUTO_SPEEDS = DriveTrain.MAX_MPS;
    }

    public static class DRIVE{
        public static final double VX_SLEW_RATE = 4.0;
        public static final double VY_SLEW_RATE = 4.0;
    }
}