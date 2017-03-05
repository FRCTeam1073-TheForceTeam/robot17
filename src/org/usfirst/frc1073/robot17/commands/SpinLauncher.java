
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
import org.usfirst.frc1073.robot17.OI;
import org.usfirst.frc1073.robot17.Robot;
import org.usfirst.frc1073.robot17.RobotMap;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class SpinLauncher extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	
	Logger log = new Logger();
	
	boolean ready = false;
	
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public SpinLauncher() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.launcher);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.bling.sendFuelLaunching();
    	
    	RobotMap.launcherlauncherMotor1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	RobotMap.launcherlauncherMotor1.changeControlMode(TalonControlMode.Speed);
    	RobotMap.launcherlauncherMotor1.reverseOutput(false);
    	RobotMap.launcherlauncherMotor1.setP(0.22);
    	RobotMap.launcherlauncherMotor1.setI(0);
    	RobotMap.launcherlauncherMotor1.setD(0.1);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	
    		double rightTriggerAxis = Robot.oi.operatorControl.getRawAxis(3);
        	double leftTriggerAxis = Robot.oi.operatorControl.getRawAxis(2);
        	
        	double encoderRPM = RobotMap.launcherlauncherMotor1.getSpeed();
        	SmartDashboard.putNumber("LauncherRPM", -encoderRPM);
        	SmartDashboard.putNumber("OI Speed", OI.speed);
        	
        	if(rightTriggerAxis >= 0.25) {
        		
        		RobotMap.launcherlauncherMotor1.enable();
        		RobotMap.launcherlauncherMotor1.set(-OI.speed);
        		
        	} 
        	else {
        		
        		RobotMap.launcherlauncherMotor1.set(0);
        		RobotMap.launcherlauncherMotor1.disable();
        		
        	}	
    	}	
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.launcherlauncherMotor1.set(0);
		RobotMap.launcherlauncherMotor1.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.launcherlauncherMotor1.set(0);
		RobotMap.launcherlauncherMotor1.disable();
    }
}
