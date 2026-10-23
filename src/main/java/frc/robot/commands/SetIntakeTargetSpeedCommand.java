package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import java.util.function.DoubleSupplier;

/** Continuously updates the intake's targetSpeed from a supplier (e.g. a joystick axis). */
public class SetIntakeTargetSpeedCommand extends Command {
  private final IntakeSubsystem intake;
  private final DoubleSupplier speedSupplier;

  public SetIntakeTargetSpeedCommand(IntakeSubsystem intake, DoubleSupplier speedSupplier) {
    this.intake = intake;
    this.speedSupplier = speedSupplier;
    addRequirements(intake);
  }

  /** Convenience constructor for a fixed speed. */
  public SetIntakeTargetSpeedCommand(IntakeSubsystem intake, double speed) {
    this(intake, () -> speed);
  }

  @Override
  public void execute() {
    intake.setTargetSpeed(speedSupplier.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
