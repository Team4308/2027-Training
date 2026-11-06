package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Turret subsystem — TalonFX (Phoenix 6), closed-loop position control. Boot position = 0 deg.
 *
 * <p>Resources:
 * <ul>
 *   <li>Subsystems: https://docs.wpilib.org/en/stable/docs/software/commandbased/subsystems.html
 *   <li>Phoenix 6 TalonFX: https://api.ctr-electronics.com/phoenix6/stable/java/com/ctre/phoenix6/hardware/TalonFX.html
 *   <li>Phoenix 6 configs: https://v6.docs.ctr-electronics.com/en/stable/docs/api-reference/api-usage/configuration.html
 *   <li>Position / Motion Magic control: https://v6.docs.ctr-electronics.com/en/stable/docs/api-reference/device-specific/talonfx/motion-magic.html
 *   <li>MathUtil.clamp: https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/MathUtil.html
 * </ul>
 */
public class Turret extends SubsystemBase {
  // TODO: Create the TalonFX motor using the CAN ID from Constants
  // TODO: Create a private target angle variable (degrees)

  public Turret() {
    // TODO: Configure PID gains and a low velocity cap (e.g. MotionMagicCruiseVelocity)
    // TODO: Print an initialization message with System.out
  }

  // TODO: Add a public setter for the target angle (degrees)
  // TODO: Add a public getter for the current angle (degrees) — needed for tolerance checks

  @Override
  public void periodic() {
    // TODO: Clamp the target angle to the soft limits from Constants
    // TODO: Convert degrees -> motor rotations using TURRET_GEAR_RATIO and send the request
    // TODO: Broadcast Target Angle and Current Angle (degrees) to SmartDashboard
  }
}
