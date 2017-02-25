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

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc1073.robot17.Robot;
import org.usfirst.frc1073.robot17.subsystems.*;

/**
 *
 */
public class AutonomousBlueGear1Fuel extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousBlueGear1Fuel() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
    	addSequential(new AutonomousBlueGear1());
		//Drives near peg and auto-drives onto it
    	switch(Robot.driveMode)
		{
			case PID:
				addSequential(new moveWithPID(97.4));
				break;
			case ROTATIONS:
				addSequential(new DriveInches(97.4));
				break;
			case TIME:
				addSequential(new TimedDrive(97.4, 0));
				break;
			default:
				break;
		}
		addSequential(new AutoTurn(.5, 147.75, "counterclockwise"));
		switch(Robot.driveMode)
		{
			case PID:
				addSequential(new moveWithPID(231.75));
				break;
			case ROTATIONS:
				addSequential(new DriveInches(231.75));
				break;
			case TIME:
				addSequential(new TimedDrive(231.75, 0));
				break;
			default:
				break;
		}
		addSequential(new AutoTurn(.5, 57.3, "clockwise"));
		//addSequential(new DriveToBoiler());
		//Drives near boiler and auto-drives into alignment
		addSequential(new AutoLaunch(.5, .5));
    } 
}
