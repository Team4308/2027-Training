package frc.robot;

/**
 * Robot-wide constants. Group related values into nested static classes.
 *
 * <p>Naming convention: constants are {@code public static final} and written in
 * UPPER_SNAKE_CASE. Include units in the name when it isn't obvious (e.g. _DEG, _RPM).
 *
 * <p>The values below are placeholders (0) — replace them with the real values from the
 * project guidelines.
 */
public final class Constants {
  private Constants() {}

  public static final class Intake {
    // Example naming convention:
    public static final int INTAKE_CAN_ID = 1;
    public static final boolean INTAKE_INVERTED = false;

    // TODO: Add intake max/min percent output clamps
    public static final int intakeMax = 100;
    public static final int intakeMin = 0;
    
  }

  public static final class Turret {
    public static final int TURRET_CAN_ID = 2;
    public static final double TURRET_GEAR_RATIO = 1; // TODO: change to the actual gear ratio
    //public static final double 
    // TODO: Add turret soft limits (degrees), velocity cap, and arrival tolerance (degrees)
    public static final int softLowLimit = -120;
    public static final int softHighLimit = 120;
    public static final int cruiseVelocity = 80;
    public static final int magicAcceleration = 160;
    public static final int magicJerk = 1600; 
  }

  public static final class Flywheel {
    public static final int FLYWHEEL_CAN_ID = 3;
    public static final double MAX_FLYWHEEL_RPM = 1;
    public static final double FLYWHEEL_GEAR_RATIO = 1; // TODO: change to the actual gear ratio

    // TODO: Add arrival tolerance (percent) and kS/kP/kI/kD gains
  }

  public static final class Controls {
    // TODO: Add controller port and JOYSTICK_DEADBAND
  }
}
