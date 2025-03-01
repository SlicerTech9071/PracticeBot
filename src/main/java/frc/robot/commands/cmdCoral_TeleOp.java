package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subCoral;

public class cmdCoral_TeleOp extends Command {
  subCoral coral;
  DoubleSupplier speed;
  public cmdCoral_TeleOp(subCoral coral, DoubleSupplier speed) {
    this.coral = coral;
    this.speed = speed;
    addRequirements(coral);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    coral.TeleOp(speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    coral.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
