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
public class AutoTurnNoGyro extends Command {
	
	private double turnSpeed;
	private double turnDegrees;
	private String turnDirection;
	private double originalDegrees;
	private double numberOfCycles;
	private double cycleCount = 0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoTurnNoGyro(double Speed, double Degrees, String Direction) {
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	turnDirection = Direction;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Logger.setLog("AutoTurnPhase: initialized");
    	if(turnDirection == "clockwise") {
    		Robot.bling.sendAutoTurnRight();
    	} else if(turnDirection == "counterclockwise") {
    		Robot.bling.sendAutoTurnLeft();
    	}
    	//RobotMap.headingGyro.reset();
    	//originalDegrees = RobotMap.headingGyro.getAngle();
    	double p = Robot.robotPreferences.getDouble("turnFactor", 4);
    	numberOfCycles=turnDegrees/p;
    	Logger.setLog("Cycles to turn: "+numberOfCycles);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnDirection == "clockwise") {
    		double right = turnSpeed;
    		double left = turnSpeed * -1;
    		Robot.driveTrain.basicDrive(left, right);	
    	} else if(turnDirection == "counterclockwise") {
    		double right = turnSpeed * -1;
    		double left = turnSpeed;
    		Robot.driveTrain.basicDrive(left, right);
    	}
    	cycleCount +=1;
    	Logger.setLog("AutoTurnPhase: execute"+Double.toString(cycleCount));
    	//Logger.setLog("Constant Degrees: "+Double.toString(RobotMap.headingGyro.getAngle()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(cycleCount > numberOfCycles) {
        	Logger.setLog("AutoTurnPhase: finishing");
    		return true;
    	} else {
    		return false;
    	}
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.basicDrive(0, 0);
    	Logger.setLog("AutoTurnPhase: end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}