package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Intake subsystem — WPI_TalonSRX (Phoenix 5), open-loop percent output.
 *
 * <p>Resources:
 * <ul>
 *   <li>Subsystems: https://docs.wpilib.org/en/stable/docs/software/commandbased/subsystems.html
 *   <li>WPI_TalonSRX: https://api.ctr-electronics.com/phoenix/latest/java/com/ctre/phoenix/motorcontrol/can/WPI_TalonSRX.html
 *   <li>ControlMode: https://api.ctr-electronics.com/phoenix/latest/java/com/ctre/phoenix/motorcontrol/ControlMode.html
 *   <li>MathUtil.clamp: https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/MathUtil.html
 *   <li>SmartDashboard: https://docs.wpilib.org/en/stable/docs/software/dashboards/smartdashboard/displaying-expressions.html
 * </ul>
 */
public class Intake extends SubsystemBase {
  // TODO: Create the WPI_TalonSRX motor using the CAN ID from Constants
  // TODO: Create a private target percent output variable

  public Intake() {
    // TODO: Apply inversion from Constants
    // TODO: Print an initialization message with System.out
  }

  // TODO: Add a public setter that only updates the private target variable

  @Override
  public void periodic() {
    // TODO: Clamp the target to the min/max percent output from Constants
    // TODO: Send it to the motor using ControlMode.PercentOutput
    // TODO: Broadcast the commanded percentage to SmartDashboard
  }
}
