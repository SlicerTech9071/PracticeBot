package frc.robot.subsystems;


import frc.robot.Constants;





import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    

    ElevatorModule ElevatorMotors = 
        new ElevatorModule(Constants.DriveConstants.rightElevatorCanId, Constants.DriveConstants.leftElecatorCanId);


    public void ElevatorUp() {
    
        ElevatorMotors.ChangeState(1);
    
    }

    public void ElevatorDown() {

        ElevatorMotors.ChangeState(-1);

    }

    public void ResetElevatorPostiton(){
        //future problem
    }


}
