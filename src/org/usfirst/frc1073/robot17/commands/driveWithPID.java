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
public class driveWithPID extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    double distinInches;
    double errorleft;
    double errorright;
    double speed;
    boolean isPressed = false;
    
	public driveWithPID(double distInInches) {
		distinInches = distInInches;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time

protected void initialize() {
		
	SmartDashboard.putString("done?", "no");

	
		Robot.bling.sendAutoDrive();
		
		//setting PID for the left side
		RobotMap.driveTrainleftMotor3.enable();
		RobotMap.driveTrainleftMotor3.setEncPosition(0);
    	RobotMap.driveTrainleftMotor3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.driveTrainleftMotor3.configEncoderCodesPerRev(360);
    	RobotMap.driveTrainleftMotor3.setProfile(0);
    	RobotMap.driveTrainleftMotor3.setF(0.1);
    	RobotMap.driveTrainleftMotor3.setP(1); //ideal p value from testing 0.485
    	RobotMap.driveTrainleftMotor3.setI(0); 
    	RobotMap.driveTrainleftMotor3.setD(0);
    	RobotMap.driveTrainleftMotor3.setAllowableClosedLoopErr(20);
    	if(Robot.robotName.equals("pb")){
    		RobotMap.driveTrainleftMotor3.reverseOutput(true);
    	}
    	else{
    		RobotMap.driveTrainleftMotor3.reverseOutput(false);
    	}

    	RobotMap.driveTrainleftMotor3.configMaxOutputVoltage(8);
    	RobotMap.driveTrainleftMotor3.changeControlMode(TalonControlMode.Speed);
    	if(Robot.robotName.equals("pb")){
    		RobotMap.driveTrainleftMotor3.set(-speed); /* one rotation is 12.566 inches */
    	}
    	else{
    		RobotMap.driveTrainleftMotor3.set(speed); /* one rotation is 12.566 inches */
    	}
    	
    	//setting PID for the right side
    	RobotMap.driveTrainrightMotor3.enable();
		RobotMap.driveTrainrightMotor3.setEncPosition(0);
    	RobotMap.driveTrainrightMotor3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.driveTrainrightMotor3.configEncoderCodesPerRev(360);
    	RobotMap.driveTrainrightMotor3.setProfile(0);
    	RobotMap.driveTrainrightMotor3.setF(0.1);
    	RobotMap.driveTrainrightMotor3.setP(1); //ideal p value from testing 0.485
    	RobotMap.driveTrainrightMotor3.setI(0); 
    	RobotMap.driveTrainrightMotor3.setD(0);
    	RobotMap.driveTrainrightMotor3.setAllowableClosedLoopErr(20);
    	if(Robot.robotName.equals("pb")){
    		RobotMap.driveTrainrightMotor3.reverseOutput(true);
    	}
    	else{
    		RobotMap.driveTrainrightMotor3.reverseOutput(false);
    	}

    	RobotMap.driveTrainrightMotor3.configMaxOutputVoltage(8);
    	RobotMap.driveTrainrightMotor3.changeControlMode(TalonControlMode.Speed);
    	if(Robot.robotName.equals("pb")){
    		RobotMap.driveTrainrightMotor3.set(-speed); /* one rotation is 12.566 inches */
    	}
    	else{
    		RobotMap.driveTrainrightMotor3.set(speed); /* one rotation is 12.566 inches */
    	}
    	
    	//waiting 60 milliseconds
    	try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = Robot.oi.driverControl.getRawAxis(4) * 1;
    	if(Robot.robotName.equals("pb")){
    		RobotMap.driveTrainrightMotor3.set(-speed); /* one rotation is 12.566 inches */
    		RobotMap.driveTrainleftMotor3.set(-speed); /* one rotation is 12.566 inches */
    	}
    	else{
    		RobotMap.driveTrainrightMotor3.set(speed); /* one rotation is 12.566 inches */
    		RobotMap.driveTrainleftMotor3.set(speed); /* one rotation is 12.566 inches */
    	}

    	errorleft = Math.abs(RobotMap.driveTrainleftMotor3.getClosedLoopError());
    	errorright = Math.abs(RobotMap.driveTrainrightMotor3.getClosedLoopError());
    	Logger.setLog("SPEED RIGHT ERROR: " + Double.toString(errorright) + "SPEED LEFT ERROR: " + Double.toString(errorleft));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	isPressed = Robot.oi.cancelAny.get();
    	
		if(Math.abs(errorright) < 100 || Math.abs(errorleft) < 100 || isPressed){
    		System.out.println("Exiting");
    		Logger.setLog("-----END OF PROGRAM-----");
    		SmartDashboard.putString("done?", "yes");
    		Robot.bling.sendEndMovePID();
    		return true;
    	}
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {    	
    	RobotMap.driveTrainleftMotor3.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveTrainrightMotor3.changeControlMode(TalonControlMode.PercentVbus);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {    	
    	RobotMap.driveTrainleftMotor3.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveTrainrightMotor3.changeControlMode(TalonControlMode.PercentVbus);
    }
}
