// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot17.subsystems;

import org.usfirst.frc1073.robot17.RobotMap;
import org.usfirst.frc1073.robot17.commands.*;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Launcher extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController conveyorMotor = RobotMap.launcherconveyorMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**Manually adding CANTalons**/
    private final CANTalon launcherMotor1 = RobotMap.launcherlauncherMotor1;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void startLauncher(double conveyorSpeed, double launcherSpeed) {
    	conveyorMotor.set(conveyorSpeed);
    	launcherMotor1.set(launcherSpeed);
    }
    
    public void stopLauncher() {
    	conveyorMotor.set(0.0);
    	launcherMotor1.set(0.0);
    }

    public void initDefaultCommand() 
    {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

