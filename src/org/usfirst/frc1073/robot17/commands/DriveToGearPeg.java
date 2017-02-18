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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1073.robot17.Robot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToGearPeg extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    NetworkTable netTable;
    public Joystick driverControl;
    public JoystickButton gearAlignBut;
    double xDelta;
    double xWidth;
    
    boolean isButtonPressed = gearAlignBut.get();
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveToGearPeg() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // Variables from the RaspberryPi via network tables
        
        
        netTable = NetworkTable.getTable("VisionTable");
        gearAlignBut = new JoystickButton(driverControl, 2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Get the numbers and print them on the smart dashboard
    	double pixToInch = ((3.53497*Math.pow(10,(-6* Math.pow(xDelta, 4))))-(8.87729*Math.pow(10,(-4 * Math.pow(xDelta, 3))))+(0.0772* Math.pow(xDelta, 2))-(3.161183*xDelta)+64.26833);
    	SmartDashboard.putNumber("inches away", pixToInch);
    	SmartDashboard.putNumber("feet away", pixToInch / 1);
        xDelta =  netTable.getNumber("centerDist", 0);
        SmartDashboard.putNumber("DriveToGearPeg xDelta", xDelta);
        xWidth =  netTable.getNumber("AverageWidth", 0);
        SmartDashboard.putNumber("DriveToGearPeg widthAvg", xWidth);
        double initialMultiplier = 4;
        double left = 0;
        double right = 0;
        //This is the basic speed - start slow
		double driveSpeed = 0.1;
		double driveSpeedMultiplier = 0;
		//Image width - 280 pixels
        double imageWidth = 280;
        if (xWidth > 50 / initialMultiplier) {
        	driveSpeedMultiplier = 1;
        }
        else {
        	driveSpeedMultiplier = initialMultiplier;
        }
        left = driveSpeed*driveSpeedMultiplier;
        right = driveSpeed*driveSpeedMultiplier;
        //Test 1 - simple left right control
        if (xDelta > 10) {
        	left = left + 0.15;
        	right = right - 0.05;
        	SmartDashboard.putString("Peg Direction", "Left");
        }
        else if (xDelta < -10) {
        	left = left -0.05;
        	right = right + 0.15;
        	SmartDashboard.putString("Peg Direction", "Right");
        }
        else {
        	left = left;
        	right = right;
        	SmartDashboard.putString("Peg Direction", "Center");
        }

        //Test 2 - left right based on how far off we are 
        /*
        double scaleFactor = driveSpeed / imageWidth;
        right = driveSpeed + (xDelta * scaleFactor); 
        left = driveSpeed - (xDelta * scaleFactor); 
        */
        
        //Test 3 - left right based on how far off we are and how far away we are 
        
        // Calculate distance based on the average width of the tape we see - the tape is 2" wide
        // Rule of 57: dist = size x ( 1/angular size in degrees) x 57
        double viewAngle=75;
        double degreesPerPixel=viewAngle/imageWidth;
        double distance = 2 * (1/(xWidth*degreesPerPixel)) * 57;
        /*
        double scaleFactor = driveSpeed / 280;
        right = driveSpeed + (xDelta * scaleFactor); 
        left = driveSpeed - (xDelta * scaleFactor); 
        */
        
        Robot.driveTrain.basicDrive(right, left);
        SmartDashboard.putNumber("peg left", left);
        SmartDashboard.putNumber("peg right", right);
        SmartDashboard.putNumber("peg distance", distance);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//test 1 - known width
    	
    	if (xWidth > 30 || isButtonPressed == true) {
    		Robot.driveTrain.basicDrive(0, 0);
    	
    		return true;
    	}
    	else {
    				
    		return false;
    }
    	//test 2 - distance
    	//copy from above
    }
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
