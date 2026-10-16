// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelSubsystem extends SubsystemBase {
  private static final int MOTOR_CAN_ID = 1;
  private final WPI_TalonSRX m_motor = new WPI_TalonSRX(MOTOR_CAN_ID);

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

  /**
   * Returns a command that runs the flywheel at the given speed and stops it when the command ends.
   *
   * @param speed Duty cycle speed from -1.0 to 1.0
   */
  public Command runFlywheelCommand(double speed) {
    return startEnd(() -> setSpeed(speed), this::stop);
  }
}

