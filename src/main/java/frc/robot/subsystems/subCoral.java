package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class subCoral extends SubsystemBase {
  SparkMax coralMotor;
  SparkMaxConfig coralConfig;
  public subCoral() {
    coralMotor = new SparkMax(Constants.CoralWheel.MotorID, MotorType.kBrushless);
    coralConfig = new SparkMaxConfig();
    coralConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake);
    coralMotor.configure(coralConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
  }

  public void TeleOp(double speed){
    coralMotor.set(speed);
  }
  public void stop(){
    coralMotor.stopMotor();
  }
}
