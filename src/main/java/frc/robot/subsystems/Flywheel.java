package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
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
  private final TalonFX m_motor = new TalonFX(Constants.Flywheel.FLYWHEEL_CAN_ID);

  private double targetRPM;
  private double rps;
  private double currentRPM;

  public Flywheel() {
    var configs = new TalonFXConfiguration();
    configs.Slot0.kP = Constants.Flywheel.kP; 
    configs.Slot0.kI = Constants.Flywheel.kI; 
    configs.Slot0.kD = Constants.Flywheel.kD; 
    configs.Slot0.kS = Constants.Flywheel.kS;
    m_motor.getConfigurator().apply(configs);
    System.out.println("Flywheel Initialized");
  }

  public void setTargetRPM(double setRPM){
    targetRPM = setRPM;
  }
  public double getCurrentRPM(){
    return m_motor.get();
  }
  @Override
  public void periodic() {
    targetRPM = MathUtil.clamp(targetRPM, Constants.Flywheel.MIN_FLYWHEEL_RPM, Constants.Flywheel.MAX_FLYWHEEL_RPM);
    rps = targetRPM * Constants.Flywheel.FLYWHEEL_GEAR_RATIO * 60;
    SmartDashboard.putNumber("Target RPM", targetRPM);
    SmartDashboard.putNumber("Current RPM", currentRPM);

  }

  // ---------------------------------------------------------------------------
  // PROVIDED: simulation support. Not part of the training exercise — do not modify.
  // The TalonFX does not move on its own in the WPILib sim, so this models the mechanism
  // and feeds position/velocity back to the simulated motor controller.
  // ---------------------------------------------------------------------------
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

    simState.setRawRotorPosition(
        m_sim.getAngularPositionRotations() * Constants.Flywheel.FLYWHEEL_GEAR_RATIO);
    simState.setRotorVelocity(
        Units.radiansToRotations(m_sim.getAngularVelocityRadPerSec())
            * Constants.Flywheel.FLYWHEEL_GEAR_RATIO);
  }
}