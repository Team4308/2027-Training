// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelSubsystem extends SubsystemBase {
  private final WPI_VictorSPX m_motor = new WPI_VictorSPX(Constants.Flywheel.flywheelId);

  public FlywheelSubsystem() {
    m_motor.configFactoryDefault();
  }

  /**
   * Sets the flywheel motor speed.
   *
   * @param speed Duty cycle speed from -1.0 to 1.0
   */
  public void setSpeed(double speed) {
    m_motor.set(speed);
  }

  /** Stops the flywheel motor. */
  public void stop() {
    m_motor.stopMotor();
  }
}

