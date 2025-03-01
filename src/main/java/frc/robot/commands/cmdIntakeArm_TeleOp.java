package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subIntakeArm;

public class cmdIntakeArm_TeleOp extends Command {
  subIntakeArm intakeArm;
  DoubleSupplier speed;
  public cmdIntakeArm_TeleOp(subIntakeArm intakeArm, DoubleSupplier speed) {
    

    this.speed = speed;
    this.intakeArm = intakeArm;
    addRequirements(intakeArm);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeArm.TeleOp(speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    intakeArm.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
