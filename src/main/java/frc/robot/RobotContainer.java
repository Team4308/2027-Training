// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.SpinFlywheelCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.SetIntakeTargetSpeedCommand;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer {
  private static final int DRIVER_CONTROLLER_PORT = 0;

  private final FlywheelSubsystem m_flywheel = new FlywheelSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final CommandXboxController m_driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Hold the A button to run the flywheel at 50% forward speed, release to stop
    m_driverController.a().whileTrue(new SpinFlywheelCommand(m_flywheel, 0.5));

    // Intake: idle at 0.5 by default
    m_intake.setDefaultCommand(new SetIntakeTargetSpeedCommand(m_intake, Constants.Intake.idleSpeed));

    // Intake: while the left joystick is moved, drive with its Y axis (up = positive)
    new Trigger(() -> Math.abs(m_driverController.getLeftY()) > Constants.Intake.joystickDeadband)
        .whileTrue(new SetIntakeTargetSpeedCommand(m_intake, () -> -m_driverController.getLeftY()));

    // Optional extra: startEnd (hold B = full reverse, release = 0) and InstantCommand (X = 0.25)
    m_driverController.b().whileTrue(m_intake.setSpeedStartEnd(-0.9));
    m_driverController.x().onTrue(m_intake.setSpeedInstant(0.25));

    // --- Example Bindings (Uncomment to use) ---

    // Hold the B button to run the flywheel in reverse at 25% speed
    // m_driverController.b().whileTrue(new SpinFlywheelCommand(m_flywheel, -0.25));

    // Press the X button once to toggle the flywheel on (75% speed) and off
    // m_driverController.x().toggleOnTrue(new SpinFlywheelCommand(m_flywheel, 0.75));

    // Press the Y button to spin the flywheel for 2 seconds, then automatically stop
    // m_driverController.y().onTrue(new SpinFlywheelCommand(m_flywheel, 0.5).withTimeout(2.0));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
