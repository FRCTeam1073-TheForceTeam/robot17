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

/**
 *
 */
public class Drive extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Drive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setPosLeft(1073.0);
    	SmartDashboard.putNumber("Position set", Robot.driveTrain.getLeftPos());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = -Robot.oi.driverLeftStick.getY();		//Invert joystick input
    	double right = -Robot.oi.driverRightStick.getY();	//Invert joystick input
//    	double left = -Robot.oi.driverLeftStick.getY();		//Invert joystick input
//    	double right = -Robot.oi.driverRightStick.getX();	//Invert joystick input
    	
    	Robot.driveTrain.basicDrive(right, left);
//    	Robot.driveTrain.arcadeDrive(left, right);
    	
    	SmartDashboard.putNumber("Left Rate", Robot.driveTrain.getLeftRate());
    	SmartDashboard.putNumber("Right Rate", Robot.driveTrain.getRightRate());
    	SmartDashboard.putNumber("Left Pos", Robot.driveTrain.getLeftPos());
    	SmartDashboard.putNumber("Right Pos", Robot.driveTrain.getRightPos());
    	System.out.println(Robot.driveTrain.getLeftPos());
    	System.out.println(Robot.driveTrain.getRightPos());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
