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
  SparkMax bottomMotor;
  SparkMax topMotor;
  SparkMaxConfig bottomConfig;
  SparkMaxConfig topConfig;
  public subIntakeWheels() {
    bottomMotor = new SparkMax(Constants.IntakeWheels.bottomMotorID, MotorType.kBrushless);
    topMotor = new SparkMax(Constants.IntakeWheels.topMotorID, MotorType.kBrushless);
    bottomConfig = new SparkMaxConfig();
    bottomConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30);
    topConfig = new SparkMaxConfig();
    topConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30);
    bottomMotor.configure(bottomConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    topMotor.configure(topConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void TeleOp(double speed){
    

    bottomMotor.set(speed);
    topMotor.set(-speed);


  }
  public void stop(){
    bottomMotor.stopMotor();
    topMotor.stopMotor();
  }
}
