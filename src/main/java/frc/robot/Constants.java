package frc.robot;

public final class Constants {
  private Constants() {}

  public static final class Intake {
    // Example naming convention:
    public static final int INTAKE_CAN_ID = 0;
    public static final boolean INTAKE_INVERTED = false;

    //Intake max/min percent output clamps
    public static final double MIN_OUTPUT = 1.0;
    public static final double MAX_OUTPUT = 0.0;

    
  }

  public static final class Turret {
    public static final int TURRET_CAN_ID = 0;
    public static final double TURRET_GEAR_RATIO = 1; // TODO: change to the actual gear ratio

    // TODO: Add turret soft limits (degrees), velocity cap, and arrival tolerance (degrees)
    public static final double TURRET_SOFT_LIMIT_FORWARD = 120;
    public static final double TURRET_SOFT_LIMIT_REVERSE = -120;
    public static final double TURRET_VELOCITY_CAP = 0;
    public static final double TURRET_ARRIVAL_TOLERANCE = 2;
  }

  public static final class Flywheel {
    public static final int FLYWHEEL_CAN_ID = 0;
    public static final double MAX_FLYWHEEL_RPM = 0;
    public static final double FLYWHEEL_GEAR_RATIO = 1; // TODO: change to the actual gear ratio

    // TODO: Add arrival tolerance (percent) and kS/kP/kI/kD gains

  public static final class Controls {
    // TODO: Add controller port and JOYSTICK_DEADBAND
    public static final double JOYSTICK_DEADBAND = 0.1;
  }
}

}
