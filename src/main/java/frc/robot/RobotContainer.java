// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turret;

/** Central hub: owns the subsystems, the controller, and the global state machine. */
public class RobotContainer {
  private State m_currentState = State.IDLE;

  private final Intake m_intake = new Intake();
  private final Turret m_turret = new Turret();
  private final Flywheel m_flywheel = new Flywheel();

  private final CommandXboxController m_controller =
      new CommandXboxController(Constants.Controls.DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_controller.x().onTrue(Commands.runOnce(() -> m_currentState = State.IDLE));
    m_controller.a().onTrue(Commands.runOnce(() -> m_currentState = State.INTAKING));
    m_controller.leftBumper().onTrue(Commands.runOnce(() -> m_currentState = State.AIMING));
    m_controller.y().onTrue(Commands.runOnce(() -> m_currentState = State.REV_FLYWHEEL));
    m_controller.rightBumper().onTrue(Commands.runOnce(() -> m_currentState = State.INTAKE_AND_SHOOT));
  }

  /** Called every loop from Robot.robotPeriodic(), after the CommandScheduler runs. */
  public void updateStateMachine() {
    SmartDashboard.putString("RobotState", m_currentState.toString());

    switch (m_currentState) {
      case IDLE:
        m_intake.setTargetPercent(0.0);
        m_turret.setTargetAngle(0.0);
        m_flywheel.setTargetRpm(0.0);
        break;

      case INTAKING:
        m_intake.setTargetPercent(0.5);
        break;

      case AIMING:
        m_intake.setTargetPercent(0.0);
        double x = m_controller.getLeftX();
        double y = -m_controller.getLeftY(); // up on the stick = positive
        if (Math.hypot(x, y) > Constants.Controls.JOYSTICK_DEADBAND) {
          // 0 deg = stick straight up, positive = stick to the right
          m_turret.setTargetAngle(Math.toDegrees(Math.atan2(x, y)));
        }
        break;

      case REV_FLYWHEEL:
        double revTarget = 0.7 * Constants.Flywheel.MAX_FLYWHEEL_RPM;
        m_flywheel.setTargetRpm(revTarget);
        if (Math.abs(m_flywheel.getCurrentRpm() - revTarget)
            <= revTarget * Constants.Flywheel.FLYWHEEL_TOLERANCE_PERCENT) {
          m_currentState = State.READY_TO_SHOOT;
        }
        break;

      case READY_TO_SHOOT:
        m_flywheel.setTargetRpm(0.7 * Constants.Flywheel.MAX_FLYWHEEL_RPM);
        break;

      case INTAKE_AND_SHOOT:
        double turretTarget = -25.0;
        m_turret.setTargetAngle(turretTarget);
        m_flywheel.setTargetRpm(0.8 * Constants.Flywheel.MAX_FLYWHEEL_RPM);
        if (Math.abs(m_turret.getCurrentAngle() - turretTarget)
            <= Constants.Turret.TURRET_TOLERANCE_DEG) {
          m_intake.setTargetPercent(0.5);
        } else {
          m_intake.setTargetPercent(0.0);
        }
        break;
    }
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
