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
import org.usfirst.frc1073.robot17.RobotMap;

/**
 *
 */
public class forward extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_dist;
    private double distInches;
    private double begin;
    private double end;
    private double startAngle;
    private double speed = .5;
    private double correctionSpeed = speed+.1;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public forward(double dist) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_dist = dist;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        distInches  = m_dist * 12;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	begin = Robot.driveTrain.getLeftDistanceInches();
    	end = begin+distInches;
    	startAngle = Math.round(RobotMap.sensorsgyro.getAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.round(RobotMap.sensorsgyro.getAngle()) <= startAngle-5)
    		Robot.driveTrain.basicDrive(correctionSpeed, speed);
    	else if(Math.round(RobotMap.sensorsgyro.getAngle()) >= startAngle+5)
    		Robot.driveTrain.basicDrive(speed, correctionSpeed);
    	else 
    		Robot.driveTrain.basicDrive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.driveTrain.getLeftDistanceInches() >= end-3 && Robot.driveTrain.getLeftDistanceInches() >= end+3) return true;
        else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.basicDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.basicDrive(0, 0);
    }
}
