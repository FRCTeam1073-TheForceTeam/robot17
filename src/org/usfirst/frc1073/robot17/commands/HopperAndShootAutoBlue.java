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
import org.usfirst.frc1073.robot17.subsystems.*;

/**
 *
 */
public class HopperAndShootAutoBlue extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public HopperAndShootAutoBlue() {

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
    	
    	addSequential(new moveWithPID(76));
    	addSequential(new AutoTurn(.5, 75, "counterclockwise"));
    	//This value needs to be changed with testing: 
    	addSequential(new moveWithPID(34));
    	addSequential(new wait());
    	//This value needs to be changed with testing: 
    	addSequential(new moveWithPID(-75));
    	addSequential(new AutoTurn(.5, 40, "counterclockwise"));
    	addSequential(new AlignAndLaunch());
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
 
    } 
}
