package frc.robot;

/**
 * The six global robot states. Do not rename or add states — every trainee's project must use
 * this same set so behaviour can be compared.
 *
 * <p>Java enums: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
public enum State {
  /** X button / boot state: intake stopped, turret to 0 deg, flywheel 0 RPM. */
  IDLE,
  /** A button: intake at a fixed 50% output. */
  INTAKING,
  /** Left bumper: left joystick X/Y sets the turret target angle (with deadband). */
  AIMING,
  /** Y button: flywheel to 70% of max RPM; auto-transitions to READY_TO_SHOOT within 3%. */
  REV_FLYWHEEL,
  /** Auto-transition: hold flywheel at the 70% target until a new state is commanded. */
  READY_TO_SHOOT,
  /** Right bumper: turret to -25 deg, flywheel 80%; intake 50% only once turret is within tolerance. */
  INTAKE_AND_SHOOT
}
