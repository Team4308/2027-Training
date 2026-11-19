// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Turret;

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
  public enum State{
    IDLE,
    INTAKING,
    AIMING,
    REV_FLYWHEEL,
    READY_TO_SHOOT,
    INTAKE_AND_SHOOT
    }
  public State currentState = State.IDLE;
  
  private static final int controllerPort = 1;
  private final Flywheel m_flywheel = new Flywheel();
  private final Turret m_Turret = new Turret();
  private final Intake m_Intake = new Intake();
  private final CommandXboxController m_driverController = new CommandXboxController(controllerPort);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_driverController.x().onTrue(Commands.runOnce(() -> currentState = State.IDLE));
    m_driverController.a().onTrue(Commands.runOnce(() -> currentState = State.INTAKING));
    m_driverController.leftBumper().onTrue(Commands.runOnce(() -> currentState = State.AIMING));
    m_driverController.y().onTrue(Commands.runOnce(() -> currentState = State.REV_FLYWHEEL));
    m_driverController.rightBumper().onTrue(Commands.runOnce(() -> currentState = State.INTAKE_AND_SHOOT));
  }

  /** Called every loop from Robot.robotPeriodic(), after the CommandScheduler runs. */
  public void updateStateMachine() {
    // TODO: Broadcast the current state to SmartDashboard (test this before any motor logic!)
    // TODO: switch (currentState) { ... } — handle every State, call subsystem setters,
    //       and perform auto-transitions (REV_FLYWHEEL -> READY_TO_SHOOT)
    switch (currentState){
      case IDLE:
        m_flywheel.setTargetRPM(0);
        m_Turret.setTargetAngle(0);
        m_Intake.intakeMotor.set(0);
        break;
      case INTAKING:
        m_Intake.intakeMotor.set(0.5);
        break;
      case AIMING:

        if(Math.hypot(m_driverController.getLeftX(), m_driverController.getLeftY()) > Constants.Controls.JOYSTICK_DEADBAND){
          m_Turret.setTargetAngle(Math.atan2(m_driverController.getLeftX(), m_driverController.getLeftY()));

        }
        break;
      case REV_FLYWHEEL:
        if(m_flywheel.getCurrentRPM() >= 0.97* 0.7 * Constants.Flywheel.MAX_FLYWHEEL_RPM){
          currentState = State.READY_TO_SHOOT;
        } 
        m_flywheel.setTargetRPM(0.7 * Constants.Flywheel.MAX_FLYWHEEL_RPM);
        break;
      case READY_TO_SHOOT:
        m_flywheel.setTargetRPM(0.7 * Constants.Flywheel.MAX_FLYWHEEL_RPM);
        break;
      case INTAKE_AND_SHOOT:
        m_Turret.setTargetAngle(-25);
        m_flywheel.setTargetRPM(0.8*Constants.Flywheel.MAX_FLYWHEEL_RPM);
        m_Intake.intakeMotor.set(0);
        if (Math.abs(m_Turret.getCurrentAngle() + 25) <= 2){
          m_Intake.intakeMotor.set(0.5);
        }
    }
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
