
package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import swervelib.parser.SwerveParser;

import yams.mechanisms.config.SwerveDriveConfig;
import yams.mechanisms.swerve.SwerveDrive;
import yams.mechanisms.swerve.utility.SwerveInputStream;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.wpilibj.DriverStation;

public class SwerveSubsystem extends SubsystemBase {

    private final SwerveDrive swerveDrive;

    public SwerveSubsystem(File directory) {

        try {
            SwerveDriveConfig config = new SwerveDriveConfig()
                    .withStartingPose(new Pose2d())
                    .withSubsystem(this);

            // Load the JSON configuration and create drivetrain
            swerveDrive = SwerveParser.parse(directory)
                    .createSwerveDrive(config);

                    setupAutoBuilder();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize swerve drive", e);
        }
    }

    @Override
    public void periodic() {
        swerveDrive.updateTelemetry();
    }

    public Command driveCommand(
            DoubleSupplier x,
            DoubleSupplier y,
            DoubleSupplier rotation) {

        SwerveInputStream input = SwerveInputStream.of(
                swerveDrive,
                x,
                y
        )
        .withControllerRotationAxis(rotation)
        .withDeadband(0.05)
        .withScaleTranslation(0.8)
        .withAllianceRelativeControl();

        return run(() -> swerveDrive.drive(() -> input.get()));
    }

    public void zeroGyro() {
        swerveDrive.zeroGyro();
    }

    public Pose2d getPose() {
        return swerveDrive.getPose();
    }

    public void resetOdometry(Pose2d pose) {
        swerveDrive.resetOdometry(pose);
    }

    public void stop() {
        swerveDrive.stop();
    }

    public void lock() {
        swerveDrive.lockPose();
    }
    public SwerveDrive getSwerveDrive() {
        return swerveDrive;
    }

    public Command getAutonomousCommand(String autoName) {
    return new PathPlannerAuto(autoName);
}

public void setupAutoBuilder() {

    try {
        RobotConfig config = RobotConfig.fromGUISettings();

        AutoBuilder.configure(
            swerveDrive::getPose,

            swerveDrive::resetOdometry,

            swerveDrive::getRobotRelativeSpeed,

            (speedsRobotRelative, feedforwards) -> {
                swerveDrive.setRobotRelativeChassisSpeeds(
                    speedsRobotRelative
                );
            },

            new PPHolonomicDriveController(
                new PIDConstants(5.0, 0.0, 0.0),
                new PIDConstants(5.0, 0.0, 0.0)
            ),

            config,

            () -> DriverStation.getAlliance()
                .map(alliance ->
                    alliance == DriverStation.Alliance.Red
                )
                .orElse(false),

            this
        );

    } catch (Exception e) {
        throw new RuntimeException(
            "Failed to configure PathPlanner AutoBuilder", e
        );
    }
}

}
