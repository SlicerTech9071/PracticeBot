package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import org.opencv.core.Mat;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeArm;

public class subIntakeArm extends SubsystemBase {
  SparkMax leftMotor;
  SparkMax rightMotor;
  AbsoluteEncoder leftEncoder;
  SparkMaxConfig leftConfig;
  SparkMaxConfig rightConfig;
  public subIntakeArm() {
    leftMotor = new SparkMax(Constants.IntakeArm.leftMotorID, MotorType.kBrushless);
    rightMotor = new SparkMax(Constants.IntakeArm.rightMotorID, MotorType.kBrushless);
    leftEncoder = leftMotor.getAbsoluteEncoder();
    leftConfig = new SparkMaxConfig();
    leftConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(50);
    leftConfig.encoder
      .positionConversionFactor(2 * Math.PI);
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
    

    SmartDashboard.putNumber("Intake Arm Encoder", GetPos().getDegrees());
  }

  public void TeleOp(double speed){
    speed = speed * Constants.IntakeArm.IntakeArmSpeed;
    if (speed == 0){
      leftMotor.setVoltage(-0.3);
      rightMotor.setVoltage(0.3);
    }else{
      leftMotor.set(-speed);
      rightMotor.set(speed);
    }
  }

public void Hold(double voltage){
  leftMotor.setVoltage(-voltage);
  rightMotor.setVoltage(voltage);
}

  public void stop(){
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }

  public Rotation2d GetPos(){
    return new Rotation2d(leftEncoder.getPosition());
  }

  public void Pos1(){
    
    double angle = GetPos().getDegrees();
    double speed = 51 - angle;
    if (angle <= 50){
      leftMotor.set(-Math.tanh(speed)/10);
      rightMotor.set(Math.tanh(speed)/10);
    }
  }
}
