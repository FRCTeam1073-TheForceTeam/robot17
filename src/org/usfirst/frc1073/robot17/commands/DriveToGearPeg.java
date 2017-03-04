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
import org.usfirst.frc1073.robot17.Logger;
import org.usfirst.frc1073.robot17.OI;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 */
public class DriveToGearPeg extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    
	//Set up for the NetworkTable variables the Raspberry Pi sends
	NetworkTable netTable;
    double xDelta;
    double xWidth;
    
    
    //Variable for button used in isFinished
    boolean isPressed = false;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveToGearPeg() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        //Sets the correct Network Table to pull from the Pixy
        netTable = NetworkTable.getTable("VisionTable");
    }

    protected void initialize() {
    	
    	SmartDashboard.putString("done?", "no");

    	Robot.bling.sendPegTargeting();
    }

    protected void execute() {
    	
    	//These are the variables that get manipulated in the code
        double forwardsRight = 0;
        double forwardsLeft = 0;
        double changeRight = 0;
        double changeLeft = 0;
        double finalRight = 0;
        double finalLeft = 0;
        
        //These are the variables for speed - start slow
        double driveMultiplier = 2;
        double changeMultiplier = 3.55;
        double driveSpeed = 0.1;
		double changeSpeed = 0.1;
		double slowWidth = 15;
		double side = 8;
		
		
		//This is the width of the Pixy
		double imageWidth = 320;
		
		//These are what the Pixy send us
		xDelta =  netTable.getNumber("centerDist", 0);
        xWidth =  netTable.getNumber("AverageWidth", 0);
		
		//This code modifies the speed based on how close you are to the peg
        if (xWidth > slowWidth) {
        	if (xWidth > slowWidth + 1) {
        		if (xWidth > slowWidth + 2) {
        			if (xWidth > slowWidth + 3) {
        				if (xWidth > slowWidth + 4) {
        					if (xWidth > slowWidth + 5) {
        						if (xWidth > slowWidth + 6) {
        							if (xWidth > slowWidth + 7) {
        								if (xWidth > slowWidth + 8) {
        									if (xWidth == slowWidth + 9) {
        					                	changeRight = changeSpeed + 0.01;
        					                	changeLeft = changeSpeed + 0.01;
        					                	forwardsRight = driveSpeed + 0.01;
        					                	forwardsLeft = driveSpeed + 0.01;
        					        		}}
        									else{
        										changeRight = changeSpeed;
        					                	changeLeft = changeSpeed;
        					                	forwardsRight = driveSpeed;
        					                	forwardsLeft = driveSpeed;
        									}}
        								else{	
        									changeRight = changeSpeed + .01;
        				                	changeLeft = changeSpeed + .01;
        				                	forwardsRight = driveSpeed + .015;
        				                	forwardsLeft = driveSpeed + .015;
        								}}
        							else{	
        								changeRight = changeSpeed + .015;
        			                	changeLeft = changeSpeed + .015;
        			                	forwardsRight = driveSpeed + .025;
        			                	forwardsLeft = driveSpeed + .025;
        							}}
        						else{		
        							changeRight = changeSpeed  + .02;
        		                	changeLeft = changeSpeed + .02;
        		                	forwardsRight = driveSpeed + .035;
        		                	forwardsLeft = driveSpeed + .035;
        		        		}}
        					else{	
        						changeRight = changeSpeed + .04;
        	                	changeLeft = changeSpeed + .04;
        	                	forwardsRight = driveSpeed + .05;
        	                	forwardsLeft = driveSpeed + .05;
        	        		}}
        				else{			
        					changeRight = changeSpeed + .05;
                        	changeLeft = changeSpeed + .05;
                        	forwardsRight = driveSpeed + .075;
                        	forwardsLeft = driveSpeed + .075;
                		}}
        			else{	
        				changeRight = changeSpeed + .06;
                    	changeLeft = changeSpeed + .06;
                    	forwardsRight = driveSpeed + .1;
                    	forwardsLeft = driveSpeed + .1;
            		}}
        		else{				
        			changeRight = changeSpeed + .1;
                	changeLeft = changeSpeed + .1;
                	forwardsRight = driveSpeed + .15;
                	forwardsLeft = driveSpeed + .15;
        		}}
        	else{	
        		changeRight = changeSpeed + .15;
            	changeLeft = changeSpeed + .15;
            	forwardsRight = driveSpeed + .15;
            	forwardsLeft = driveSpeed + .15;
        	}
        
        
        //This code handles the left and right vs forward motion of the robot
        //based on the Pixy's values
        if (xDelta > side) {
        	finalRight = forwardsRight - changeRight;
        	finalLeft = forwardsLeft + changeLeft;
        	SmartDashboard.putString("Peg Direction", "Left");
        }
        else if (xDelta < -side) {
        	finalRight = forwardsRight + changeRight;
        	finalLeft = forwardsLeft - changeLeft;
        	SmartDashboard.putString("Peg Direction", "Right");
        }
        else {
        	finalRight = forwardsRight;
        	finalLeft = forwardsLeft;
        	SmartDashboard.putString("Peg Direction", "Center");
        }
        
        //This sends the final numbers to the drivetrain
        Robot.driveTrain.basicDrive(finalRight, finalLeft);
       
    }
    
    protected boolean isFinished() {
    	
    	//Checks the cancel button for its state
    	isPressed = Robot.oi.cancelAny.get();
    	
    	//Stops the robot if:
    	/*
    	 * Change this to affect how close the robot gets. Bigger means closer and smaller means farther.
    	 * 27 should be ok to start.
    	 * 			|
    	 * 			|
    	 * 			|
    	 *			|
    	 *		   \ /
    	 */
    	if (xWidth > 25 || isPressed) {
    		Robot.driveTrain.basicDrive(0, 0);
    		Robot.oi.driverControl.rumbleTimeRep(1, 150, 2);
    		SmartDashboard.putString("done?", "yes");
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    protected void end() {
    	//Stops motors and sets bling
    	Robot.driveTrain.basicDrive(0, 0);
    	Robot.bling.sendRemoveGear();
    }

    protected void interrupted() {
    	//Stops motors and sets bling
    	Robot.driveTrain.basicDrive(0, 0);
    	Robot.bling.sendRemoveGear();
    }
}