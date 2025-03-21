package frc.robot.subsystems;

import java.lang.management.MemoryType;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class subElevator extends SubsystemBase {
  SparkMax leftMotor;
  SparkMax rightMotor;
  SparkMaxConfig leftConfig = new SparkMaxConfig();
  SparkMaxConfig rightConfig = new SparkMaxConfig();
  RelativeEncoder leftEncoder;

  public subElevator() {
    leftMotor = new SparkMax(Constants.Elevator.leftMotorID, MotorType.kBrushless);
    rightMotor = new SparkMax(Constants.Elevator.rightMotorID, MotorType.kBrushless);

    leftConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(50);

    rightConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(50);

    leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    leftEncoder = leftMotor.getEncoder();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Encoder", leftEncoder.getPosition());
  }

  public void TeleOp(double speed){
    speed = speed * Constants.Elevator.elevatorSpeed;
    if (speed == 0){
      leftMotor.setVoltage(0.30);
      rightMotor.setVoltage(-0.30);
    }else{
      leftMotor.set(speed);
      rightMotor.set(-speed);
    }
  }

  public void AutoOp(double speed){
    speed = speed * Constants.Elevator.elevatorSpeed;

    leftMotor.set(speed);
    rightMotor.set(-speed);

  }

  public void stop(){
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }

  public void testss(){
    rightMotor.setVoltage(-1);
  }
}
