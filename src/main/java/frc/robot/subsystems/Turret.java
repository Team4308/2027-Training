package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
  // PROVIDED (used by the simulation code at the bottom of this file) — do not rename.
  private final TalonFX m_motor = new TalonFX(Constants.Turret.TURRET_CAN_ID);

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

  // ---------------------------------------------------------------------------
  // PROVIDED: simulation support. Not part of the training exercise — do not modify.
  // The TalonFX does not move on its own in the WPILib sim, so this models the mechanism
  // and feeds position/velocity back to the simulated motor controller.
  // ---------------------------------------------------------------------------
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

    simState.setRawRotorPosition(
        m_sim.getAngularPositionRotations() * Constants.Turret.TURRET_GEAR_RATIO);
    simState.setRotorVelocity(
        Units.radiansToRotations(m_sim.getAngularVelocityRadPerSec())
            * Constants.Turret.TURRET_GEAR_RATIO);
  }
}
