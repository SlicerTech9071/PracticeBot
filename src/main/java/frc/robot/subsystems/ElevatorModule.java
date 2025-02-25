package frc.robot.subsystems;


import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Configs;
import frc.robot.Constants;

public class ElevatorModule {
    
    private SparkMax m_RightSparkMax;
    private SparkMax m_LeftSparkMax;

    private final RelativeEncoder m_RightEncoder;
    private final RelativeEncoder m_LeftEncoder;

    private SparkClosedLoopController m_RightClosedLoopController;
    private SparkClosedLoopController m_LeftClosedLoopController;

    public ElevatorModule(int RightCANId, int LeftCANId){

        m_RightSparkMax = new SparkMax(RightCANId, MotorType.kBrushless);
        m_LeftSparkMax = new SparkMax(LeftCANId, MotorType.kBrushless);

        m_RightEncoder = m_RightSparkMax.getEncoder();
        m_LeftEncoder = m_LeftSparkMax.getEncoder();

        m_RightClosedLoopController = m_RightSparkMax.getClosedLoopController();
        m_LeftClosedLoopController = m_LeftSparkMax.getClosedLoopController();

        m_RightSparkMax.configure(Configs.Elevator.elevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_LeftSparkMax.configure(Configs.Elevator.elevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    }

    //1 to go up
    //-1 to go down
    public void ChangeElevatorState(int UpTrue){

        m_RightClosedLoopController.setReference(Constants.DriveConstants.kElevatorSpeed * UpTrue,ControlType.kVelocity);
        m_LeftClosedLoopController.setReference(Constants.DriveConstants.kElevatorSpeed * UpTrue * -1, ControlType.kVelocity);

    }
    public void ChangeShootState(int Clockwise){

        m_RightClosedLoopController.setReference(Constants.DriveConstants.kShootSpeed * Clockwise, ControlType.kVelocity);
        m_LeftClosedLoopController.setReference(Constants.DriveConstants.kShootSpeed * Clockwise, ControlType.kVelocity);


    }

    public double[] getPos(){
        double PosArray[] = {m_RightEncoder.getPosition(),m_LeftEncoder.getPosition()};
        return PosArray;
    }
}
