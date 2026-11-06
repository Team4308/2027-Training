package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Flywheel subsystem — TalonFX (Phoenix 6), closed-loop velocity control.
 *
 * <p>Resources:
 * <ul>
 *   <li>Subsystems: https://docs.wpilib.org/en/stable/docs/software/commandbased/subsystems.html
 *   <li>Phoenix 6 TalonFX: https://api.ctr-electronics.com/phoenix6/stable/java/com/ctre/phoenix6/hardware/TalonFX.html
 *   <li>Velocity closed loop: https://v6.docs.ctr-electronics.com/en/stable/docs/api-reference/device-specific/talonfx/closed-loop-requests.html
 *   <li>Gains (kS/kP/kI/kD): https://v6.docs.ctr-electronics.com/en/stable/docs/api-reference/device-specific/talonfx/basic-pid-control.html
 *   <li>MathUtil.clamp: https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/MathUtil.html
 * </ul>
 */
public class Flywheel extends SubsystemBase {
  // TODO: Create the TalonFX motor using the CAN ID from Constants
  // TODO: Create a private target RPM variable

  public Flywheel() {
    // TODO: Configure kS/kP/kI/kD gains from Constants
    // TODO: Print an initialization message with System.out
  }

  // TODO: Add a public setter for the target RPM
  // TODO: Add a public getter for the current RPM — needed for the 3% arrival check

  @Override
  public void periodic() {
    // TODO: Clamp the target RPM against MAX_FLYWHEEL_RPM
    // TODO: Convert RPM -> rotations per second using FLYWHEEL_GEAR_RATIO and send the request
    // TODO: Broadcast Target RPM and Current RPM to SmartDashboard
  }
}
