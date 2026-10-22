package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlywheelSubsystem;

public class SpinFlywheelCommand extends Command {
  private final FlywheelSubsystem flywheel;
  private final double speed;

  public SpinFlywheelCommand(FlywheelSubsystem flywheel, double speed) {
    this.flywheel = flywheel;
    this.speed = speed;
    addRequirements(flywheel);
  }

  @Override
  public void initialize() {
    // 1. Setup / Actuation: Command the motor setpoint when the command begins
    flywheel.setSpeed(speed);
  }

  @Override
  public void execute() {
    // 2. Periodic loop (runs every 20ms while active):
    // Useful for telemetry, feedforward adjustments, or closed-loop monitoring
    SmartDashboard.putNumber("Flywheel/TargetSpeed", speed);
  }

  @Override
  public void end(boolean interrupted) {
    // 3. Cleanup: Always cut power when the command finishes or gets cancelled
    flywheel.stop();
    SmartDashboard.putNumber("Flywheel/TargetSpeed", 0.0);
  }

  @Override
  public boolean isFinished() {
    // 4. Lifecycle boundary: Return false so it stays active until an external trigger
    // (such as releasing a button, timing out, or subsystem interruption) ends it.
    return false;
  }
}