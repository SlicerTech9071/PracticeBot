package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class subIntakeWheels extends SubsystemBase {
  SparkMax leftMotor;
  SparkMax rightMotor;
  SparkMaxConfig leftConfig;
  SparkMaxConfig rightConfig;
  public subIntakeWheels() {
    leftMotor = new SparkMax(Constants.IntakeWheels.leftMotorID, MotorType.kBrushless);
    rightMotor = new SparkMax(Constants.IntakeWheels.rightMotorID, MotorType.kBrushless);
    leftConfig = new SparkMaxConfig();
    leftConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(20);
    rightConfig = new SparkMaxConfig();
    rightConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(20);
    leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void TeleOp(double speed){
    

    leftMotor.set(speed);
    rightMotor.set(-speed);


  }
  public void stop(){
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }
}
