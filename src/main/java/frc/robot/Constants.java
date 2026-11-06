package frc.robot;

import edu.wpi.first.math.MathUtil;

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
    public static final int INTAKE_CAN_ID = 0;
    public static final boolean INTAKE_INVERTED = false;

    // TODO: Add intake max/min percent output clamps
    private final double MIN_OUTPUT;
    private final double MAX_OUTPUT;

    public void runIntake(double demand) {
        double clampedDemand = MathUtil.clamp(demand, MIN_OUTPUT, MAX_OUTPUT);
        
  }

  public static final class Turret {
    public static final int TURRET_CAN_ID = 0;
    public static final double TURRET_GEAR_RATIO = 1; // TODO: change to the actual gear ratio

    // TODO: Add turret soft limits (degrees), velocity cap, and arrival tolerance (degrees)
  }

  public static final class Flywheel {
    public static final int FLYWHEEL_CAN_ID = 0;
    public static final double MAX_FLYWHEEL_RPM = 0;
    public static final double FLYWHEEL_GEAR_RATIO = 1; // TODO: change to the actual gear ratio

    // TODO: Add arrival tolerance (percent) and kS/kP/kI/kD gains
  }

  public static final class Controls {
    // TODO: Add controller port and JOYSTICK_DEADBAND
  }
}
