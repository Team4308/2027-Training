package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Flywheel subsystem — TalonFX (Phoenix 6), closed-loop velocity control. */
public class Flywheel extends SubsystemBase {
  private final TalonFX m_motor = new TalonFX(Constants.Flywheel.FLYWHEEL_CAN_ID);
  private final VelocityVoltage m_request = new VelocityVoltage(0);

  private double m_targetRpm = 0.0;

  public Flywheel() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Slot0.kS = Constants.Flywheel.FLYWHEEL_KS;
    config.Slot0.kV = Constants.Flywheel.FLYWHEEL_KV;
    config.Slot0.kP = Constants.Flywheel.FLYWHEEL_KP;
    config.Slot0.kI = Constants.Flywheel.FLYWHEEL_KI;
    config.Slot0.kD = Constants.Flywheel.FLYWHEEL_KD;
    m_motor.getConfigurator().apply(config);
    System.out.print("Flywheel initialized\n");
  }

  // --- Simulation: the TalonFX does not move on its own in sim, so model the mechanism ---
  private final DCMotorSim m_sim = new DCMotorSim(
      LinearSystemId.createDCMotorSystem(
          DCMotor.getKrakenX60(1), 0.002, Constants.Flywheel.FLYWHEEL_GEAR_RATIO),
      DCMotor.getKrakenX60(1));

  @Override
  public void simulationPeriodic() {
    TalonFXSimState simState = m_motor.getSimState();
    simState.setSupplyVoltage(RobotController.getBatteryVoltage());

    m_sim.setInputVoltage(simState.getMotorVoltage());
    m_sim.update(0.020);

    // The sim models the flywheel (mechanism); the rotor spins GEAR_RATIO times faster
    simState.setRawRotorPosition(
        m_sim.getAngularPositionRotations() * Constants.Flywheel.FLYWHEEL_GEAR_RATIO);
    simState.setRotorVelocity(
        Units.radiansToRotations(m_sim.getAngularVelocityRadPerSec())
            * Constants.Flywheel.FLYWHEEL_GEAR_RATIO);
  }

  /** Sets the target flywheel speed in RPM. Clamped in periodic(). */
  public void setTargetRpm(double rpm) {
    m_targetRpm = rpm;
  }

  /** Returns the current flywheel speed in RPM. */
  public double getCurrentRpm() {
    return m_motor.getVelocity().getValueAsDouble() / Constants.Flywheel.FLYWHEEL_GEAR_RATIO * 60.0;
  }

  @Override
  public void periodic() {
    double clamped = MathUtil.clamp(m_targetRpm, 0.0, Constants.Flywheel.MAX_FLYWHEEL_RPM);
    double motorRps = clamped / 60.0 * Constants.Flywheel.FLYWHEEL_GEAR_RATIO;
    m_motor.setControl(m_request.withVelocity(motorRps));

    SmartDashboard.putNumber("Flywheel/TargetRPM", clamped);
    SmartDashboard.putNumber("Flywheel/CurrentRPM", getCurrentRpm());
  }
}
