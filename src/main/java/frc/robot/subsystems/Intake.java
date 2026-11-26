package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Intake subsystem — WPI_TalonSRX (Phoenix 5), open-loop percent output. */
public class Intake extends SubsystemBase {
  private final WPI_TalonSRX m_motor = new WPI_TalonSRX(Constants.Intake.INTAKE_CAN_ID);

  private double m_targetPercent = 0.0;

  public Intake() {
    m_motor.configFactoryDefault();
    m_motor.setInverted(Constants.Intake.INTAKE_INVERTED);
    System.out.print("Intake initialized (inverted: " + Constants.Intake.INTAKE_INVERTED + ")\n");
  }

  /** Sets the target percent output (-1.0 to 1.0). Clamped in periodic(). */
  public void setTargetPercent(double percent) {
    m_targetPercent = percent;
  }

  @Override
  public void periodic() {
    double output = MathUtil.clamp(
        m_targetPercent, Constants.Intake.INTAKE_MIN_PERCENT, Constants.Intake.INTAKE_MAX_PERCENT);
    m_motor.set(ControlMode.PercentOutput, output);
    SmartDashboard.putNumber("Intake/CommandedPercent", output);
  }
}
