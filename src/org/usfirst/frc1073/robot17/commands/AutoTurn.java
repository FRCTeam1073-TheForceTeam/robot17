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
import org.usfirst.frc1073.robot17.Robot;

/**
 *
 */
public class AutoTurn extends Command {
	
	private double turnSpeed;
	private double turnDistance;
	private String turnDirection;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoTurn(double Speed, double Distance, String Direction) {
    	turnSpeed = Speed;
    	turnDistance = Distance;
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnDirection != null) {
    		double right = turnSpeed * -1;
    		double left = turnSpeed;
    		Robot.driveTrain.basicDrive(left, right);
    	} else {
    		double right = turnSpeed;
    		double left = turnSpeed * -1;
    		Robot.driveTrain.basicDrive(left, right);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(turnDirection != null) {
    		return Robot.driveTrain.getRightDistanceInches() >= turnDistance || Robot.driveTrain.getLeftDistanceInches() >= (turnDistance * -1);
    	} else {
    		return Robot.driveTrain.getRightDistanceInches() >= (turnDistance * -1) || Robot.driveTrain.getLeftDistanceInches() >= turnDistance;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.basicDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}