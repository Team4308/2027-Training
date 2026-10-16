// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.FlywheelSubsystem;

public class RobotContainer {
  private static final int DRIVER_CONTROLLER_PORT = 0;

  private final FlywheelSubsystem m_flywheel = new FlywheelSubsystem();
  private final CommandXboxController m_driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Hold the A button to run the flywheel at 50% forward speed, release to stop
    m_driverController.a().whileTrue(m_flywheel.runFlywheelCommand(0.5));

    // --- Example Bindings (Uncomment to use) ---

    // Hold the B button to run the flywheel in reverse at 25% speed
    // m_driverController.b().whileTrue(m_flywheel.runFlywheelCommand(-0.25));

    // Press the X button once to toggle the flywheel on (75% speed) and off
    // m_driverController.x().toggleOnTrue(m_flywheel.runFlywheelCommand(0.75));

    // Press the Y button to spin the flywheel for 2 seconds, then automatically stop
    // m_driverController.y().onTrue(m_flywheel.runFlywheelCommand(0.5).withTimeout(2.0));

    // Use the right trigger axis as a variable speed control (default command)
    // m_flywheel.setDefaultCommand(
    //     m_flywheel.run(() -> m_flywheel.setSpeed(m_driverController.getRightTriggerAxis())));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
