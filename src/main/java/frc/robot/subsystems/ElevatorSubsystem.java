package frc.robot.subsystems;


import frc.robot.Constants;





import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    

    ElevatorModule ElevatorMotors = 
        new ElevatorModule(Constants.DriveConstants.rightElevatorCanId, Constants.DriveConstants.leftElecatorCanId);

    ElevatorModule ShooterMotors = 
        new ElevatorModule(Constants.DriveConstants.rightShootCanId, Constants.DriveConstants.leftShootCanId);

    ElevatorModule HeadMotors =
        new ElevatorModule(Constants.DriveConstants.rightHeadCanId, Constants.DriveConstants.leftHeadCanId);
    
    public void ElevatorUp() {
    
        ElevatorMotors.ChangeElevatorState(1);
    
    }

    public void ElevatorDown() {

        ElevatorMotors.ChangeElevatorState(-1);
    }

    public void SuckIn() {
        ShooterMotors.ChangeShootState(-1);
    }

    public void ShootOut() {
        ShooterMotors.ChangeShootState(1);
    }

    public void HeadDown(){

        if (HeadMotors.getPos()[0] != 0 && HeadMotors.getPos()[1] != 0){
            HeadMotors.ChangeElevatorState(-1);
        }

    }
}
