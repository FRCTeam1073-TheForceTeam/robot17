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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot17.Robot;
import org.usfirst.frc1073.robot17.RobotMap;

/**
 *
 */
public class MoveAway extends Command {
	static int count;
	public static boolean gearOut = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public MoveAway() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearOut = true;
    	SmartDashboard.putBoolean("GearIn", gearOut);
    	Robot.bling.sendMoveAway();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.driveTrainGearSensor.getVoltage() < 1.0)
    	{
    		count++;
    	}else
    	{
    		count = 0;
    	}
    	SmartDashboard.putNumber("Voltage", RobotMap.driveTrainGearSensor.getVoltage());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if(count < 40)
			return false;
		else
			Robot.bling.sendEndMoveAway();
			return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearOut = false;
    	SmartDashboard.putBoolean("GearIn", gearOut);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
