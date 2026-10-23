package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Second motor subsystem, driven by a Talon SRX. */
public class IntakeSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_motor = new WPI_TalonSRX(Constants.Intake.intakeId);

  /** Speed/direction the motor is run at every periodic loop (-1.0 to 1.0). */
  private double targetSpeed = 0.0;

  public IntakeSubsystem() {
    m_motor.configFactoryDefault();

    // Check inversion, print it, and invert if not already inverted
    boolean inverted = m_motor.getInverted();
    System.out.print("Intake motor inverted: " + inverted + "\n");
    if (!inverted) {
      m_motor.setInverted(true);
      System.out.print("Intake motor was not inverted, now inverted\n");
    }
  }

  /**
   * Sets the target speed, clamped to the configured max speeds.
   *
   * @param speed Duty cycle speed from -1.0 to 1.0
   */
  public void setTargetSpeed(double speed) {
    targetSpeed = MathUtil.clamp(
        speed, Constants.Intake.maxNegativeSpeed, Constants.Intake.maxPositiveSpeed);
  }

  @Override
  public void periodic() {
    m_motor.set(targetSpeed);
  }

  // --- Optional extra: simple command factories ---

  /** Sets targetSpeed while held, resets to 0 when the command ends (use with a button binding). */
  public Command setSpeedStartEnd(double speed) {
    return startEnd(() -> setTargetSpeed(speed), () -> setTargetSpeed(0.0));
  }

  /** Sets targetSpeed once and finishes immediately. */
  public Command setSpeedInstant(double speed) {
    return new InstantCommand(() -> setTargetSpeed(speed), this);
  }
}
