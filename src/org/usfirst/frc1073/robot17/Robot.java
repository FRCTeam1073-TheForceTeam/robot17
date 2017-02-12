// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot17;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot17.commands.*;
import org.usfirst.frc1073.robot17.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    boolean blueAlliance;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Launcher launcher;
    public static Collector collector;
    public static Climber climber;
    public static LEDs lEDs;
    public static Agitator agitator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**Adding sendable choosers**/
    public static SendableChooser blueAutonomousChooser;
    public static SendableChooser redAutonomousChooser;
    public static SendableChooser allianceChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        launcher = new Launcher();
        collector = new Collector();
        climber = new Climber();
        lEDs = new LEDs();
        agitator = new Agitator();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        //Resets gyro
        RobotMap.driveTrainHeadingGyro.reset();
        //Calibrates the gyro
        RobotMap.driveTrainHeadingGyro.calibrate();
        
        /**Blue alliance autonomous chooser**/
        blueAutonomousChooser = new SendableChooser();
        blueAutonomousChooser.addDefault("Do Nothing", null);
        blueAutonomousChooser.addObject("Blue Gear 1", new AutonomousBlueGear1());
        blueAutonomousChooser.addObject("Blue Gear 2", new AutonomousBlueGear2());
        blueAutonomousChooser.addObject("Blue Gear 3", new AutonomousBlueGear3());
        blueAutonomousChooser.addObject("Blue Gear 1 Fuel", new AutonomousBlueGear1Fuel());
        blueAutonomousChooser.addObject("Blue Gear 2 Fuel", new AutonomousBlueGear2Fuel());
        blueAutonomousChooser.addObject("Blue Gear 3 Fuel", new AutonomousBlueGear3Fuel());
        
        /**Red alliance autonomous chooser**/
        redAutonomousChooser = new SendableChooser();
        redAutonomousChooser.addDefault("Do Nothing", null);
        redAutonomousChooser.addObject("Red Gear 1", new AutonomousRedGear1());
        redAutonomousChooser.addObject("Red Gear 2", new AutonomousRedGear2());
        redAutonomousChooser.addObject("Red Gear 3", new AutonomousRedGear3());
        redAutonomousChooser.addObject("Red Gear 1 Fuel", new AutonomousRedGear1Fuel());
        redAutonomousChooser.addObject("Red Gear 2 Fuel", new AutonomousRedGear2Fuel());
        redAutonomousChooser.addObject("Red Gear 3 Fuel", new AutonomousRedGear3Fuel());
        
        /**Alliance chooser**/
        //TODO If the default alliance always shows up, set default to null
        allianceChooser = new SendableChooser();
        allianceChooser.addDefault("Blue Alliance", blueAlliance = true);
        allianceChooser.addObject("Red Alliance", blueAlliance = false);
        SmartDashboard.putData("Alliance Chooser", allianceChooser);
        
        /**Determines which autonomous set to put on the dashboard**/
        if (blueAlliance == true) SmartDashboard.putData("Blue Alliance Autonomous Chooser", blueAutonomousChooser);
        if (blueAlliance == false) SmartDashboard.putData("Red Alliance Autonomous Chooser", redAutonomousChooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	if (blueAlliance == true) autonomousCommand = (Command) blueAutonomousChooser.getSelected();
        if (blueAlliance == false) autonomousCommand = (Command) redAutonomousChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
