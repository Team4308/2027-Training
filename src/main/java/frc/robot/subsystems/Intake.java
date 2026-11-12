package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
  public static int intakeOutput;
  WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.Intake.INTAKE_CAN_ID);

  
  public Intake() {
    // TODO: Apply inversion from Constants
    // TODO: Print an initialization message with System.out
    var intakeConfigs = new TalonSRXConfiguration();
    System.out.println(intakeConfigs);
    System.out.println("Initialised. The intake inversion is " + Constants.Intake.INTAKE_INVERTED + ".");
    intakeMotor.setInverted(Constants.Intake.INTAKE_INVERTED);
  }
  // TODO: Add a public setter that only updates the private target variable
  @Override
  public void periodic() {
    MathUtil.clamp(intakeOutput, Constants.Intake.intakeMin, Constants.Intake.intakeMax);
    intakeMotor.set(ControlMode.PercentOutput, intakeOutput);
    SmartDashboard.putNumber("Intake Output", intakeOutput);
  }
}
