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

import com.ctre.CANTalon.FeedbackDevice;

/**
 *
 */
public class AutoDrive extends Command {
	
	private double autoDriveSpeed;
	private double autoDriveDistance;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoDrive(double Speed, double Distance) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	
    	autoDriveSpeed = Speed;
    	autoDriveDistance = Distance;
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {    
    	SmartDashboard.putString("Drive1Finished", "Initialized");
    	//Reset the encoders beofre using them
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.basicDrive(autoDriveSpeed, autoDriveSpeed);
    	SmartDashboard.putNumber("LeftInches", Robot.driveTrain.getLeftDistanceInches());
    	SmartDashboard.putNumber("RightInches", Robot.driveTrain.getRightDistanceInches());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.getLeftDistanceInches() >= autoDriveDistance || Robot.driveTrain.getRightDistanceInches() >= autoDriveDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.basicDrive(0, 0);
    	SmartDashboard.putBoolean("Drive1Finished", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
