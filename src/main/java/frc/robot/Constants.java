package frc.robot;

/**
 * Robot-wide constants. Group related values into nested static classes.
 *
 * <p>Naming convention: constants are {@code public static final} and written in
 * UPPER_SNAKE_CASE. Include units in the name when it isn't obvious (e.g. _DEG, _RPM).
 */
public final class Constants {
  private Constants() {}

  public static final class Intake {
    public static final int INTAKE_CAN_ID = 1;
    public static final boolean INTAKE_INVERTED = false;

    // Percent output clamps (-1.0 to 1.0)
    public static final double INTAKE_MAX_PERCENT = 0.8;
    public static final double INTAKE_MIN_PERCENT = -0.8;
  }

  public static final class Turret {
    public static final int TURRET_CAN_ID = 2;

    /** Motor rotations per one full turret rotation. */
    public static final double TURRET_GEAR_RATIO = 10.0;

    // Soft limits (degrees)
    public static final double TURRET_MIN_ANGLE_DEG = -120.0;
    public static final double TURRET_MAX_ANGLE_DEG = 120.0;

    /** Closed-loop velocity cap (motor rotations per second) so the turret pans slowly. */
    public static final double TURRET_MAX_VELOCITY_RPS = 2.0;
    public static final double TURRET_ACCELERATION_RPS2 = 4.0;

    /** Arrival tolerance: +/- degrees. */
    public static final double TURRET_TOLERANCE_DEG = 2.0;

    // Position PID gains (Slot 0)
    public static final double TURRET_KP = 4.0;
    public static final double TURRET_KI = 0.0;
    public static final double TURRET_KD = 0.0;
  }

  public static final class Flywheel {
    public static final int FLYWHEEL_CAN_ID = 3;

    /** Motor rotations per one flywheel rotation. */
    public static final double FLYWHEEL_GEAR_RATIO = 1.0;
    public static final double MAX_FLYWHEEL_RPM = 6000.0;

    /** Arrival tolerance as a fraction of the target (0.03 = 3%). */
    public static final double FLYWHEEL_TOLERANCE_PERCENT = 0.03;

    // Velocity gains (Slot 0)
    public static final double FLYWHEEL_KS = 0.1;
    public static final double FLYWHEEL_KV = 0.12;
    public static final double FLYWHEEL_KP = 0.2;
    public static final double FLYWHEEL_KI = 0.0;
    public static final double FLYWHEEL_KD = 0.0;
  }

  public static final class Controls {
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final double JOYSTICK_DEADBAND = 0.1;
  }
}
