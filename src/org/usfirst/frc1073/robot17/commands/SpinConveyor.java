// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot17.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1073.robot17.Logger;
import org.usfirst.frc1073.robot17.Robot;
import org.usfirst.frc1073.robot17.RobotMap;

/**
 *
 */
public class SpinConveyor extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SpinConveyor() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.conveyor);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    
    protected void initialize() {
    }

    
    protected void execute() {
    	//Sets up the Operator Controller axis to a usable varible
    	double leftTriggerAxis = Robot.oi.operatorControl.getRawAxis(2);
    	
    	//Checks Axis and starts or stops the conveyor based on it
    	if(leftTriggerAxis >= 0.25) {
    		RobotMap.conveyorconveyorMotor.set(-15);
    	} 
    	else {
    		RobotMap.conveyorconveyorMotor.set(0);
    	}	
    }
    
    
    protected boolean isFinished() {
        return false;
    }

    
    protected void end() {
    }


    protected void interrupted() {
    	RobotMap.conveyorconveyorMotor.set(0);
    }
}
