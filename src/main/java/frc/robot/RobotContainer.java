
package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.SpinFlywheelCommand;
import frc.robot.commands.SetIntakeTargetSpeedCommand;

import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RobotContainer {

    private static final int DRIVER_CONTROLLER_PORT = 0;
    private final SendableChooser<Command> autoChooser;
    
    private final FlywheelSubsystem m_flywheel =
        new FlywheelSubsystem();

    private final IntakeSubsystem m_intake =
        new IntakeSubsystem();

    private final SwerveSubsystem swerve =
        new SwerveSubsystem(
            new File(Filesystem.getDeployDirectory(), "swerve")
        );

    private final CommandXboxController m_driverController =
        new CommandXboxController(DRIVER_CONTROLLER_PORT);
        

    public RobotContainer() {

        NamedCommands.registerCommand("SpinFlywheel", new SpinFlywheelCommand(m_flywheel, 0.5));
        configureBindings();

        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);

        swerve.setDefaultCommand(
            swerve.driveCommand(
                () -> -m_driverController.getLeftY(),
                () -> -m_driverController.getLeftX(),
                () -> -m_driverController.getRightX()
            )
        );

        m_driverController.start().onTrue(
            Commands.runOnce(() -> swerve.zeroGyro())
        );
        
    }

    private void configureBindings() {

        m_driverController.a().whileTrue(
            new SpinFlywheelCommand(m_flywheel, 0.5)
        );

        m_intake.setDefaultCommand(
            new SetIntakeTargetSpeedCommand(
                m_intake,
                Constants.Intake.idleSpeed
            )
        );

        new Trigger(
            () -> Math.abs(m_driverController.getLeftY())
                > Constants.Intake.joystickDeadband
        ).whileTrue(
            new SetIntakeTargetSpeedCommand(
                m_intake,
                () -> -m_driverController.getLeftY()
            )
        );

        m_driverController.b().whileTrue(
            m_intake.setSpeedStartEnd(-0.9)
        );

        m_driverController.x().onTrue(
            m_intake.setSpeedInstant(0.25)
        );
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}