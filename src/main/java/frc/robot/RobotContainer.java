package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.cmdCoral_TeleOp;
import frc.robot.commands.cmdElevator_TeleOp;
import frc.robot.commands.cmdIntakeArm_TeleOp;
import frc.robot.commands.cmdIntakeWheels_TeleOp;
import frc.robot.subsystems.subSwerve;
import frc.robot.subsystems.subCoral;
import frc.robot.subsystems.subElevator;
import frc.robot.subsystems.subIntakeArm;
import frc.robot.subsystems.subIntakeWheels;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

public class RobotContainer {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  // The robot's subsystems
  private final subSwerve m_robotDrive = new subSwerve();
  private final subCoral coral = new subCoral();
  private final subElevator elevator = new subElevator();
  private final subIntakeArm intakeArm = new subIntakeArm();
  private final subIntakeWheels intakeWheels = new subIntakeWheels();

  // The driver's controller
  CommandXboxController driverOne = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandXboxController driverTwo = new CommandXboxController(OIConstants.kOperatorControllerPort);

  public RobotContainer() {
    ConfigureDriverOne();
    ConfigureDriverTwo();      
  }

  private void ConfigureDriverOne() {
    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(driverOne.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(driverOne.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(driverOne.getRightX(), OIConstants.kDriveDeadband),
                true),
            m_robotDrive));

    driverOne.a().whileTrue(new InstantCommand(()->m_robotDrive.zeroHeading()));
    driverOne.y().whileTrue(new RunCommand(() -> m_robotDrive.Center(0.1, 0.85, 0.2, 18, 0.01, 1), m_robotDrive));
  }

  private void ConfigureDriverTwo() {
    elevator.setDefaultCommand(new cmdElevator_TeleOp(elevator, () -> driverTwo.getLeftY() * -1));
    intakeArm.setDefaultCommand(new cmdIntakeArm_TeleOp(intakeArm, ()-> driverTwo.getRightY()*-1));
    driverTwo.rightBumper().whileTrue(new cmdIntakeWheels_TeleOp(intakeWheels, ()-> 0.80));
    driverTwo.leftBumper().whileTrue(new cmdIntakeWheels_TeleOp(intakeWheels, ()->-0.50));
    //driverTwo.rightTrigger().whileTrue(new cmdCoral_TeleOp(coral, ()->0.4));
    //driverTwo.leftTrigger().whileTrue(new cmdCoral_TeleOp(coral, ()->-0.4));
  }

  public void postTelemetry() {

    try {
      SmartDashboard.putNumber("gyroscope angle", m_robotDrive.getHeading());
      SmartDashboard.putNumber("LimeLightX", table.getEntry("tx").getDouble(0));
      SmartDashboard.putNumber("LimeLightY", table.getEntry("ty").getDouble(0));
      //SmartDashboard.putNumber("LimilightSkew", table.getEntry("t2d")[0].getAsDouble(0));
    } catch (Exception e){
      System.out.println("error");
    }
  }
  
  public Command getAutonomousCommand() {
    return Commands.sequence(
      // Drive forward
      new RunCommand(() -> m_robotDrive.drive(0.5, 0, 0, false), m_robotDrive).withTimeout(1),
      // Stop
      new RunCommand(() -> m_robotDrive.drive(0, 0, 0, false), m_robotDrive).withTimeout(1)
    );
    // // Create config for trajectory
    // TrajectoryConfig config = new TrajectoryConfig(
    //     AutoConstants.kMaxSpeedMetersPerSecond,
    //     AutoConstants.kMaxAccelerationMetersPerSecondSquared)
    //     // Add kinematics to ensure max speed is actually obeyed
    //     .setKinematics(DriveConstants.kDriveKinematics);

    // // An example trajectory to follow. All units in meters.
    // Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
    //     // Start at the origin facing the +X direction
    //     new Pose2d(0, 0, new Rotation2d(0)),
    //     // Pass through these two interior waypoints, making an 's' curve path
    //     List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
    //     // End 3 meters straight ahead of where we started, facing forward
    //     new Pose2d(3, 0, new Rotation2d(0)),
    //     config);

    // var thetaController = new ProfiledPIDController(
    //     AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    // thetaController.enableContinuousInput(-Math.PI, Math.PI);

    // SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
    //     exampleTrajectory,
    //     m_robotDrive::getPose, // Functional interface to feed supplier
    //     DriveConstants.kDriveKinematics,

    //     // Position controllers
    //     new PIDController(AutoConstants.kPXController, 0, 0),
    //     new PIDController(AutoConstants.kPYController, 0, 0),
    //     thetaController,
    //     m_robotDrive::setModuleStates,
    //     m_robotDrive);

    // // Reset odometry to the starting pose of the trajectory.
    // m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

    // // Run path following command, then stop at the end.
    // return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false));
  }
}
