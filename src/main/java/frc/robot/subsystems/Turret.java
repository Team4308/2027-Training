package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
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

/** Turret subsystem — TalonFX (Phoenix 6), closed-loop position control. Boot position = 0 deg. */
public class Turret extends SubsystemBase {
  private final TalonFX m_motor = new TalonFX(Constants.Turret.TURRET_CAN_ID);
  private final MotionMagicVoltage m_request = new MotionMagicVoltage(0);

  private double m_targetAngleDeg = 0.0;

  public Turret() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Slot0.kP = Constants.Turret.TURRET_KP;
    config.Slot0.kI = Constants.Turret.TURRET_KI;
    config.Slot0.kD = Constants.Turret.TURRET_KD;
    // Low velocity cap so the turret pans slowly
    config.MotionMagic.MotionMagicCruiseVelocity = Constants.Turret.TURRET_MAX_VELOCITY_RPS;
    config.MotionMagic.MotionMagicAcceleration = Constants.Turret.TURRET_ACCELERATION_RPS2;
    m_motor.getConfigurator().apply(config);

    // Boot position is assumed to be 0 degrees
    m_motor.setPosition(0.0);
    System.out.print("Turret initialized\n");
  }

  // --- Simulation: the TalonFX does not move on its own in sim, so model the mechanism ---
  private final DCMotorSim m_sim = new DCMotorSim(
      LinearSystemId.createDCMotorSystem(
          DCMotor.getKrakenX60(1), 0.05, Constants.Turret.TURRET_GEAR_RATIO),
      DCMotor.getKrakenX60(1));

  @Override
  public void simulationPeriodic() {
    TalonFXSimState simState = m_motor.getSimState();
    simState.setSupplyVoltage(RobotController.getBatteryVoltage());

    m_sim.setInputVoltage(simState.getMotorVoltage());
    m_sim.update(0.020);

    // The sim models the turret (mechanism); the rotor spins GEAR_RATIO times faster
    simState.setRawRotorPosition(
        m_sim.getAngularPositionRotations() * Constants.Turret.TURRET_GEAR_RATIO);
    simState.setRotorVelocity(
        Units.radiansToRotations(m_sim.getAngularVelocityRadPerSec())
            * Constants.Turret.TURRET_GEAR_RATIO);
  }

  /** Sets the target angle in degrees. Clamped in periodic(). */
  public void setTargetAngle(double degrees) {
    m_targetAngleDeg = degrees;
  }

  /** Returns the current turret angle in degrees. */
  public double getCurrentAngle() {
    return m_motor.getPosition().getValueAsDouble() / Constants.Turret.TURRET_GEAR_RATIO * 360.0;
  }

  @Override
  public void periodic() {
    double clamped = MathUtil.clamp(
        m_targetAngleDeg,
        Constants.Turret.TURRET_MIN_ANGLE_DEG,
        Constants.Turret.TURRET_MAX_ANGLE_DEG);
    double motorRotations = clamped / 360.0 * Constants.Turret.TURRET_GEAR_RATIO;
    m_motor.setControl(m_request.withPosition(motorRotations));

    SmartDashboard.putNumber("Turret/TargetAngleDeg", clamped);
    SmartDashboard.putNumber("Turret/CurrentAngleDeg", getCurrentAngle());
  }
}
