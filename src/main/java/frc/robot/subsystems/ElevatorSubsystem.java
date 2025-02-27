package frc.robot.subsystems;


import frc.robot.Configs;
import frc.robot.Constants;


import edu.wpi.first.wpilibj2.command.SubsystemBase;


//Class that was first just for the elevator, but know kinda has everthing.
//Don't know if that is a good way to do it
public class ElevatorSubsystem extends SubsystemBase{
    

    MotorModule ElevatorMotors = 
        new MotorModule(Constants.DriveConstants.rightElevatorCanId, Constants.DriveConstants.leftElecatorCanId, Configs.Elevator.elevatorConfig);

    MotorModule AlgaeShooterMotors = 
        new MotorModule(Constants.DriveConstants.rightShootCanId, Constants.DriveConstants.leftShootCanId, Configs.Algae.AlgaeIntakeConfig);

    MotorModule HeadMotors =
        new MotorModule(Constants.DriveConstants.rightHeadCanId, Constants.DriveConstants.leftHeadCanId, Configs.Head.HeadMotorConfig);
    
    // public void ElevatorUp() {
    
    //     ElevatorMotors.ChangeElevatorState(1);
    
    // }


    public void ElevatorDrive(boolean ButtonUp, boolean ButtonDown){

        if (ButtonUp){
            ElevatorMotors.ChangeState(1, Constants.DriveConstants.kElevatorSpeed);
        }
        else if(ButtonDown){
            ElevatorMotors.ChangeState(-1, Constants.DriveConstants.kElevatorSpeed);
        }
        else{
            ElevatorMotors.StopMotor();
        }

    }
}
