package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeArm;

public class subIntakeArm extends SubsystemBase {
  SparkMax leftMotor;
  SparkMax rightMotor;
  RelativeEncoder leftEncoder;
  SparkMaxConfig leftConfig;
  SparkMaxConfig rightConfig;
  public subIntakeArm() {
    leftMotor = new SparkMax(Constants.IntakeArm.leftMotorID, MotorType.kBrushless);
    rightMotor = new SparkMax(Constants.IntakeArm.rightMotorID, MotorType.kBrushless);
    leftEncoder = leftMotor.getEncoder();
    leftConfig = new SparkMaxConfig();
    leftConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(50);
    rightConfig = new SparkMaxConfig();
    rightConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(50);
    leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake Arm Encoder", leftEncoder.getPosition());
  }

  public void TeleOp(double speed){
    speed = speed * Constants.IntakeArm.IntakeArmSpeed;
    if (speed == 0 && leftEncoder.getPosition() > 1){
      leftMotor.setVoltage(0.22);
      rightMotor.setVoltage(-0.22);
    }else{
      leftMotor.set(speed);
      rightMotor.set(-speed);
    }
  }
  public void stop(){
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }
}
