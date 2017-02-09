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

import org.usfirst.frc1073.robot17.Logger;
import org.usfirst.frc1073.robot17.Robot;
import org.usfirst.frc1073.robot17.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class pidMove extends Command {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    double rate;
    double position;
    double count;
    double raw;
    double error;
    double distance;
    double speed;
    double temp;
    double enc;
    double encv;
    double setp;
    double dis;
    
	public pidMove(double Distance) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		
		dis = Distance;
    }

    // Called just before this Command runs the first time

	protected void initialize() {
		
		RobotMap.pIDDriveleftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		RobotMap.pIDDriveleftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
		RobotMap.pIDDriverightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
		RobotMap.pIDDriverightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
	    	
		RobotMap.pIDDriveleftMotor1.enable();
		
    	RobotMap.pIDDriveleftMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

    	RobotMap.pIDDriveleftMotor1.setProfile(0);
    	RobotMap.pIDDriveleftMotor1.setF(0.1097);
    	RobotMap.pIDDriveleftMotor1.setP(1.1); //ideal p value from testing 0.485 of launcher speed
    	RobotMap.pIDDriveleftMotor1.setI(0); 
    	RobotMap.pIDDriveleftMotor1.setD(2.3);
    	RobotMap.pIDDriveleftMotor1.reverseOutput(true);
    	RobotMap.pIDDriveleftMotor1.setForwardSoftLimit(100000);
    	RobotMap.pIDDriveleftMotor1.enableForwardSoftLimit(true);
    	RobotMap.pIDDriveleftMotor1.setReverseSoftLimit(-100000);
    	RobotMap.pIDDriveleftMotor1.enableReverseSoftLimit(true);
    	RobotMap.pIDDriveleftMotor1.setCloseLoopRampRate(2);

    	RobotMap.pIDDriveleftMotor1.configMaxOutputVoltage(4);
    	RobotMap.pIDDriveleftMotor1.changeControlMode(TalonControlMode.Position);
    	RobotMap.pIDDriveleftMotor1.set((-dis) * (4*Math.PI)); /*set in rotations... one rotation is 12.566 inches */ 
    	
    	
    	
    	RobotMap.pIDDriverightMotor1.enable();
		
    	RobotMap.pIDDriverightMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

    	RobotMap.pIDDriverightMotor1.setProfile(0);
    	RobotMap.pIDDriverightMotor1.setF(0.1097);
    	RobotMap.pIDDriverightMotor1.setP(1.1); //ideal p value from testing 0.485 of launcher speed
    	RobotMap.pIDDriverightMotor1.setI(0); 
    	RobotMap.pIDDriverightMotor1.setD(2.3);
    	RobotMap.pIDDriverightMotor1.reverseOutput(true);
    	RobotMap.pIDDriverightMotor1.setForwardSoftLimit(100000);
    	RobotMap.pIDDriverightMotor1.enableForwardSoftLimit(true);
    	RobotMap.pIDDriverightMotor1.setReverseSoftLimit(-100000);
    	RobotMap.pIDDriverightMotor1.enableReverseSoftLimit(true);
    	RobotMap.pIDDriverightMotor1.setCloseLoopRampRate(2);

    	RobotMap.pIDDriverightMotor1.configMaxOutputVoltage(4);
    	RobotMap.pIDDriverightMotor1.changeControlMode(TalonControlMode.Position);
    	RobotMap.pIDDriverightMotor1.set(dis * (4*Math.PI)); /*set in rotations... one rotation is 12.566 inches */ 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("isFinish");
    	if(Math.abs(RobotMap.pIDDriveleftMotor1.getClosedLoopError()) < 25){
    		System.out.println("Exiting");
    		return false;
    	}
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.pIDDriveleftMotor1.reset();
    	RobotMap.pIDDriveleftMotor1.disable();
    	RobotMap.pIDDriveleftMotor1.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	RobotMap.pIDDriveleftMotor1.reset();
    	RobotMap.pIDDriveleftMotor1.disable();
    	RobotMap.pIDDriveleftMotor1.set(0);
    }
}
