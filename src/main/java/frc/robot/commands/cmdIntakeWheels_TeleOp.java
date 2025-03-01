package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subIntakeWheels;

public class cmdIntakeWheels_TeleOp extends Command {
  subIntakeWheels intakeWheels;
  DoubleSupplier speed;
  public cmdIntakeWheels_TeleOp(subIntakeWheels intakeWheels, DoubleSupplier speed) {
    this.intakeWheels = intakeWheels;
    this.speed = speed;
    addRequirements(intakeWheels);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeWheels.TeleOp(speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    intakeWheels.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
