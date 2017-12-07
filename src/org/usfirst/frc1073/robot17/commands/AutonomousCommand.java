package org.usfirst.frc1073.robot17.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1073.robot17.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Auto command for myTurret:
 *	
 *	Seeks a designated target and tracks it when found.
 *	
 *	By Nathaniel and Myles!
 *	Featuring panny and tilty!
 *	Sponsered by Lays! "Stay Wavy"
 */
public class AutonomousCommand extends Command {

	NetworkTable netTable;
	double xDelta;
	double xWidth;
	double yDelta;
	double yWidth;
	double blockCount;
	
	double horizontalPos;
	double verticalPos;
	
	double horizontalDir;
	double verticalDir;
	
	public AutonomousCommand() {
		netTable = NetworkTable.getTable("TurretTable");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		horizontalPos = .5;
		verticalPos = .05;
		
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	// Pulls variables from Network Tables
		xDelta =  netTable.getNumber("centerDistX", 0);
		xWidth =  netTable.getNumber("AverageWidth", 0);
		yDelta =  netTable.getNumber("centerDistY", 0);
		yWidth =  netTable.getNumber("AverageHeight", 0);
		blockCount = netTable.getNumber("Blocks", 0);
	
	// Defines speed and slow down markers
		double horizontalIncrement = 0.02;
		double verticalIncrement = 0.01;
		double horizontalEndMove = 0;
		double verticalEndMove = 0;
		double side = 8; // Marks the reasonable area around the center	
		
	// Puts variables from Network Tables on SmartDashboard
		SmartDashboard.putNumber("xDelta", xDelta);
		SmartDashboard.putNumber("xWidth", xWidth);
		SmartDashboard.putNumber("yDelta", yDelta);
		SmartDashboard.putNumber("yWidth", yWidth);
		SmartDashboard.putNumber("Block Count", blockCount);
		
		double pannyGet = RobotMap.pannyA.getVoltage();
		double tiltyGet = RobotMap.tiltyA.getVoltage();
		
		SmartDashboard.putNumber("panny", pannyGet);
		SmartDashboard.putNumber("tilty", tiltyGet);
	
	// This instantiates "Limit switch" booleans that act on voltage coming from the servos
	// to locate where each servo is facing. Thus using the limits of these servos and the corresponding
	// voltages associated with them we can set limits so that the robot doesn't damage itself
		// Left limit
        if (RobotMap.panny.get() < RobotMap.lowP){
        	RobotMap.leftLimit = true;
        }
        else {
        	RobotMap.leftLimit = false;
        }
        // Right limit
        if (RobotMap.panny.get() > RobotMap.highP){
        	RobotMap.rightLimit = true;
        }
        else {
        	RobotMap.rightLimit = false;
        }
        // Lower limit
        if (RobotMap.tilty.get() < RobotMap.lowT){
        	RobotMap.lowerLimit = true;
        }
        else {
        	RobotMap.lowerLimit = false;
        }
        // Upper limit
        if (RobotMap.tilty.get() > RobotMap.highT){
        	RobotMap.upperLimit = true;
        }
        else {
        	RobotMap.upperLimit = false;
        }
		
	// BLockCount asks the Pixy how many things it sees
	// when it sees something, we track it
		if (blockCount > 0) {
			SmartDashboard.putString("Current State", "Targeting (" + blockCount + ")");
			
	// Increases speed of the turrets rotation depending on 
	// how far the target is to the left or right
	// by increasing the size of the increment
			if (Math.abs(xDelta) > side) {
				if (Math.abs(xDelta) > side + 5) {
					// Second fastest
					horizontalEndMove = (2 * horizontalIncrement);
				}
				if (Math.abs(xDelta) > side + 15){
					// Fastest speed
					horizontalEndMove = (3 * horizontalIncrement);
				}
				else {
					// Third fastest
					horizontalEndMove = (1 * horizontalIncrement);
				}
			}
			else {
				// Nothing changes
				horizontalEndMove = 0;
			}
			
	// This code handles the left and right motion of the turret
	// based on the Pixy's values
			if (xDelta > side) {
				horizontalEndMove = horizontalPos - horizontalEndMove;
				SmartDashboard.putString("Target", "Right");
			}
			else if (xDelta < -side) {
				horizontalEndMove = horizontalPos + horizontalEndMove;
				SmartDashboard.putString("Target", "Left");
			}
			else {
				horizontalEndMove = horizontalPos;
				SmartDashboard.putString("Target", "Centered");
			}
			
	// This code sends the info to the panny
	// as long as it isn't hitting a limit switch.
			if (RobotMap.leftLimit == false && RobotMap.rightLimit == false) {
				RobotMap.panny.set(horizontalEndMove);
			}
			else if (RobotMap.leftLimit == true && xDelta > 0) {
				RobotMap.panny.set(horizontalEndMove);
			}
			else if (RobotMap.rightLimit == true && xDelta < 0) {
				RobotMap.panny.set(horizontalEndMove);
			}
			else {
				RobotMap.panny.set(verticalPos);
			}
			
	// Increases speed of the turrets vertical rotation depending on 
	// how far the target is up or down by
	// increasing the size of the increment
			if (Math.abs(yDelta) > side) {
				if (Math.abs(yDelta) > side + 5) {
					// Second fastest
					verticalEndMove = (2 * verticalIncrement);
				}
				if (Math.abs(yDelta) > side + 15){
					// Fastest speed
					verticalEndMove = (3 * verticalIncrement);
				}
				else {
					// Third fastest
					verticalEndMove = (1 * verticalIncrement);
				}
			}
			else {
				// Nothing changes
				verticalEndMove = 0;
			}
			
	// This code handles the up and down motion of the turret
	// based on the Pixy's values
			if (yDelta > side) {
				verticalEndMove = verticalPos - verticalEndMove;
				SmartDashboard.putString("TargetVert", "Down");
			}
			else if (yDelta < -side) {
				verticalEndMove = verticalPos + verticalEndMove;
				SmartDashboard.putString("TargetVert", "Up");
			}
			else {
				verticalEndMove = verticalPos;
				SmartDashboard.putString("TargetVert", "Centered");
			}
			
	// This code sends the info to the tilty
	// as long as it isn't hitting a limit switch
			if (RobotMap.lowerLimit == false && RobotMap.upperLimit == false) {
				RobotMap.tilty.set(verticalEndMove);
			}
			else if (RobotMap.lowerLimit == true && yDelta > 0) {
				RobotMap.tilty.set(verticalEndMove);
			}
			else if (RobotMap.upperLimit == true && yDelta < 0) {
				RobotMap.tilty.set(verticalEndMove);
			}
			else {
				RobotMap.tilty.set(verticalPos);
			}
		}
		
	// When no blocks are seen, we strafe back and forth, and up and down,
	// while the turret looks for the target
		else {
			SmartDashboard.putString("Current State", "Searching (" + blockCount + ")");
			
			// Left and right
			if (RobotMap.leftLimit == true) {
				RobotMap.panny.set(1);
			}
			else if (RobotMap.rightLimit == true) {
				RobotMap.panny.set(0);
			}
			else {
				RobotMap.panny.set(0);
			}
			// Up and down
			if (RobotMap.upperLimit == true) {
				RobotMap.tilty.set(0);
			}
			else if (RobotMap.lowerLimit == true) {
				RobotMap.tilty.set(1);
			}
			else {
				RobotMap.tilty.set(1);
			}
		}
	
	// Puts the speeds of the motors on SmartDashboard
		SmartDashboard.putNumber("Vertical Speed", verticalEndMove);
		SmartDashboard.putNumber("Horizontal Speed", horizontalEndMove);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.panny.set(.5);
		RobotMap.tilty.set(.5);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		RobotMap.panny.set(.5);
		RobotMap.tilty.set(.5);
		
	}
}
