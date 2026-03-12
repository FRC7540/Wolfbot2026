// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems
        private final DriveSubsystem m_robotDrive = new DriveSubsystem();
        private final IntakeSubsystem intake = new IntakeSubsystem(
                        Constants.IntakeConstants.kIntakeMotorCanId,
                        Constants.IntakeConstants.kStorageMotorCanId);
        private final ShooterSubsystem shooter = new ShooterSubsystem(
                        Constants.ShooterConstants.kShooterMotorUpperCanId,
                        Constants.ShooterConstants.kShooterMotorLowerCanId);
        private final ClimberSubsystem m_robotClimb = new ClimberSubsystem(
                        Constants.ClimberConstants.kClimbingMotorCanId,
                        Constants.ClimberConstants.kLimitSwitch);

        // The driver's controller
        XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
        XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the button bindings
                configureButtonBindings();

                // Configure default commands
                m_robotDrive.setDefaultCommand(
                                // The left stick controls translation of the robot.
                                // Turning is controlled by the X axis of the right stick.
                                new RunCommand(
                                                () -> m_robotDrive.drive(
                                                                // input translation reduced to 10%
                                                                -MathUtil.applyDeadband(
                                                                                m_driverController.getLeftY() * 0.5,
                                                                                OIConstants.kDriveDeadband),
                                                                -MathUtil.applyDeadband(
                                                                                m_driverController.getLeftX() * 0.5,
                                                                                OIConstants.kDriveDeadband),
                                                                -MathUtil.applyDeadband(
                                                                                m_driverController.getRightX() * 0.5,
                                                                                OIConstants.kDriveDeadband),
                                                                true),
                                                m_robotDrive));

                intake.setDefaultCommand(
                                // The default will do nothing
                                new RunCommand(
                                                () -> intake.stop_intake(),
                                                intake));
                shooter.setDefaultCommand(new RunCommand(() -> shooter.disable_shooter(), shooter));

                m_robotClimb.setDefaultCommand(
                                // The default will do nothing
                                new RunCommand(
                                                () -> m_robotClimb.disable_climber(),
                                                m_robotClimb));

        }

        /**
         * Use this method to define your button->command mappings. Buttons can be
         * created by
         * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
         * subclasses ({@link
         * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
         * passing it to a
         * {@link JoystickButton}.
         */
        private void configureButtonBindings() {
                new JoystickButton(m_driverController, Button.kR1.value)
                                .whileTrue(new RunCommand(
                                                () -> m_robotDrive.setX(),
                                                m_robotDrive));

                new JoystickButton(m_driverController, XboxController.Button.kStart.value)
                                .onTrue(new InstantCommand(
                                                () -> m_robotDrive.zeroHeading(),
                                                m_robotDrive));

                // Operator controls
                // Intake (Right Bumper, R1)
                new JoystickButton(m_operatorController, Button.kR1.value)
                                .whileTrue(new RunCommand(
                                                () -> intake.run_intake(),
                                                intake));
                // Shoot (A Button)
                new JoystickButton(m_operatorController, XboxController.Button.kA.value)
                                .whileTrue(new SequentialCommandGroup(new RunCommand(
                                                () -> shooter.enable_upper_shooter(),
                                                shooter)
                                                .withTimeout(.5),
                                                new ParallelCommandGroup(
                                                                new RunCommand(
                                                                                () -> shooter.enable_upper_shooter(),
                                                                                shooter),
                                                                new RunCommand(
                                                                                () -> intake.feed_shooter(),
                                                                                intake))));
                // Outtake (Left Bumper, L1)
                new JoystickButton(m_operatorController, Button.kL1.value)
                                .whileTrue(new RunCommand(
                                                () -> intake.run_outtake(),
                                                intake));
                // Extend Climber (X Button)
                new JoystickButton(m_operatorController, XboxController.Button.kX.value)
                                .whileTrue(new RunCommand(
                                                () -> m_robotClimb.climber_extend(),
                                                m_robotClimb));
                // Retract Climber (B Button)
                new JoystickButton(m_operatorController, XboxController.Button.kB.value)
                                .whileTrue(new RunCommand(
                                                () -> m_robotClimb.climber_retract(),
                                                m_robotClimb));
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // // Create config for trajectory
                // TrajectoryConfig config = new TrajectoryConfig(
                // AutoConstants.kMaxSpeedMetersPerSecond,
                // AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                // // Add kinematics to ensure max speed is actually obeyed
                // .setKinematics(DriveConstants.kDriveKinematics);

                // // An example trajectory to follow. All units in meters.
                // Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                // // Start at the origin facing the +X direction
                // new Pose2d(0, 0, new Rotation2d(0)),
                // // Pass through these two interior waypoints, making an 's' curve path
                // List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                // // End 3 meters straight ahead of where we started, facing forward
                // new Pose2d(3, 0, new Rotation2d(0)),
                // config);

                // var thetaController = new ProfiledPIDController(
                // AutoConstants.kPThetaController, 0, 0,
                // AutoConstants.kThetaControllerConstraints);
                // thetaController.enableContinuousInput(-Math.PI, Math.PI);

                // SwerveControllerCommand swerveControllerCommand = new
                // SwerveControllerCommand(
                // exampleTrajectory,
                // m_robotDrive::getPose, // Functional interface to feed supplier
                // DriveConstants.kDriveKinematics,

                // // Position controllers
                // new PIDController(AutoConstants.kPXController, 0, 0),
                // new PIDController(AutoConstants.kPYController, 0, 0),
                // thetaController,
                // m_robotDrive::setModuleStates,
                // m_robotDrive);

                // // Reset odometry to the starting pose of the trajectory.
                // m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

                // // Run path following command, then stop at the end.
                // return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0,
                // false));

                return new SequentialCommandGroup(
                                new RunCommand(
                                                () -> shooter.enable_upper_shooter(),
                                                shooter)
                                                .withTimeout(.5),
                                new ParallelCommandGroup(
                                                new RunCommand(
                                                                () -> intake.feed_shooter(),
                                                                intake),
                                                new RunCommand(
                                                                () -> shooter.enable_upper_shooter(),
                                                                shooter)

                                ).withTimeout(10));
        }
}
