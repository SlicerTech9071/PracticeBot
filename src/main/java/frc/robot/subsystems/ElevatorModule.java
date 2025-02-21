package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig;

import frc.robot.Configs;
import frc.robot.Constants;

public class ElevatorModule {
    
    private SparkMax m_RightSparkMax;
    private SparkMax m_LeftSparkMax;

    private SparkClosedLoopController m_RightClosedLoopController;
    private SparkClosedLoopController m_LeftClosedLoopController;

    public ElevatorModule(int RightCANId, int LeftCANId){

        m_RightSparkMax = new SparkMax(RightCANId, MotorType.kBrushless);
        m_LeftSparkMax = new SparkMax(LeftCANId, MotorType.kBrushless);

        m_RightClosedLoopController = m_RightSparkMax.getClosedLoopController();
        m_LeftClosedLoopController = m_LeftSparkMax.getClosedLoopController();

        m_RightSparkMax.configure(Configs.Elevator.elevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_LeftSparkMax.configure(Configs.Elevator.elevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


    }

    //1 to go up
    //-1 to go down
    public void ChangeState(int UpTrue){

        m_RightClosedLoopController.setReference(Constants.DriveConstants.kElevatorSpeed * UpTrue,ControlType.kVelocity);
        m_LeftClosedLoopController.setReference(Constants.DriveConstants.kElevatorSpeed * UpTrue * -1, ControlType.kVelocity);

    }

}
