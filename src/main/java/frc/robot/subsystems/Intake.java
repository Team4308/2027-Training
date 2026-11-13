package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

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
  private final WPI_TalonSRX m_motor = new WPI_TalonSRX(Constants.Intake.INTAKE_CAN_ID);
  private double targetPercentOutput = 0.03;
  // TODO: Create a private target percent output variable

  public Intake() {
    // TODO: Apply inversion from Constants
    // TODO: Print an initialization message with System.out
    System.out.println("Intake Initialized.");
    m_motor.configFactoryDefault();
    m_motor.setInverted(Constants.Intake.INTAKE_INVERTED);
  }

  // TODO: Add a public setter that only updates the private target variable
    public void setTargetPercentOut(double Percent) {
    targetPercentOutput = Percent;
  }

  @Override
  public void periodic() {
    targetPercentOutput = MathUtil.clamp(targetPercentOutput, Constants.Intake.MIN_OUTPUT, Constants.Intake.MAX_OUTPUT);
      m_motor.set(ControlMode.PercentOutput, targetPercentOutput);
      SmartDashboard.putNumber("Target Intake Output", targetPercentOutput);
    // TODO: Clamp the target to the min/max percent output from Constants
    // TODO: Send it to the motor using ControlMode.PercentOutput
    // TODO: Broadcast the commanded percentage to SmartDashboard
  }
}
