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

import org.usfirst.frc1073.robot17.RobotMap;
import org.usfirst.frc1073.robot17.Robot;

/**
 *
 */
public class PIDTurn extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public PIDTurn() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pIDDrive);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    double error;
    double angle;
    static double c = 0.03;
    double p, i, d;


    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.pIDDrivegyro.reset();
    	Robot.pIDDrive.getPIDController().reset();
    	Robot.pIDDrive.setSetpoint(-50); // 50 degrees for now
//    	SmartDashboard.getNumber("P", p);
//    	SmartDashboard.getNumber("I", i);
//    	SmartDashboard.getNumber("D", d);
//    	Robot.pIDDrive.getPIDController().setPID(p, i, d);
    	//Robot.pIDDrive.
    	Robot.pIDDrive.enable();
    	Robot.pIDDrive.setAbsoluteTolerance(10);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = Robot.pIDDrive.getPIDController().getError();
    	SmartDashboard.putNumber("Error",error );
    	angle = RobotMap.pIDDrivegyro.getAngle();
    	SmartDashboard.putNumber("Angle",angle );
    	RobotMap.pIDDriveleftMotor1.set(-0.3);
    	RobotMap.pIDDriverightMotor1.set(0.3);
    	//Robot.pIDDrive.basicDrive(-0.3, 0.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(Robot.pIDDrive.getPIDController().getError() ) < 5)
    		return true;
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pIDDrive.disable();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}