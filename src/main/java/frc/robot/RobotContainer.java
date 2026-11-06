// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

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
  // TODO: Create the global State variable, initialized to State.IDLE
  // TODO: Create the Intake, Turret, and Flywheel subsystems
  // TODO: Create the CommandXboxController

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // TODO: Map buttons to states, e.g.
    //   controller.x().onTrue(Commands.runOnce(() -> currentState = State.IDLE));
    // X = IDLE, A = INTAKING, Left Bumper = AIMING, Y = REV_FLYWHEEL, Right Bumper = INTAKE_AND_SHOOT
  }

  /** Called every loop from Robot.robotPeriodic(), after the CommandScheduler runs. */
  public void updateStateMachine() {
    // TODO: Broadcast the current state to SmartDashboard (test this before any motor logic!)
    // TODO: switch (currentState) { ... } — handle every State, call subsystem setters,
    //       and perform auto-transitions (REV_FLYWHEEL -> READY_TO_SHOOT)
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
