// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
/**
 * Central hub: owns the subsystems, the controller, and the global state machine.
 *
 * <p>Resources:
 * <ul>
 *   <li>Structuring a project: https://docs.wpilib.org/en/stable/docs/software/commandbased/structuring-command-based-project.html
 *   <li>Binding triggers: https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
 *   <li>CommandXboxController: https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/CommandXboxController.html
 *   <li>Commands.runOnce: https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/Commands.html
 *   <li>Java switch: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
 * </ul>
 */
public class RobotContainer {
  public enum State {
    IDLE,
    INTAKING,
    AIMING,
    REV_FLYWHEEL,
    INTAKE_AND_SHOOT,
  }
  private State m_currentState = State.IDLE;
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.Controls.DRIVER_CONTROLLER_PORT);
  // TODO: Create the Intake, Turret, and Flywheel subsystems
  // TODO: Create the CommandXboxController

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // TODO: Map buttons to states, e.g.
    //controller.x().onTrue(Commands.runOnce(() -> currentState = State.IDLE));
    // X = IDLE, A = INTAKING, Left Bumper = AIMING, Y = REV_FLYWHEEL, Right Bumper = INTAKE_AND_SHOOT
    m_driverController.x().onTrue(Commands.runOnce(() -> m_currentState = State.IDLE));
  }

  /** Called every loop from Robot.robotPeriodic(), after the CommandScheduler runs. */
  public void updateStateMachine() {
      SmartDashboard.putNumber("Current state", m_currentState == State.INTAKING ? 1.0 : 0.0);
    // TODO: Broadcast the current state to SmartDashboard (test this before any motor logic!)
    // TODO: switch (currentState) { ... } — handle every State, call subsystem setters,
    //       and perform auto-transitions (REV_FLYWHEEL -> READY_TO_SHOOT)
  
// There is a similar part in State.java
    switch (m_currentState) {
      case IDLE:
        m_currentState = State.IDLE;
        break;
      case INTAKING:
        m_currentState = State.INTAKING;
        break;
      case AIMING:
        m_currentState = State.AIMING;
        break;
      case REV_FLYWHEEL:
        m_currentState = State.REV_FLYWHEEL;
        break;
      case INTAKE_AND_SHOOT:
        m_currentState = State.INTAKE_AND_SHOOT;
        break;
      default:
        break;
    }
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
