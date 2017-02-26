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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.core.Mat;

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
    boolean blueAlliance = false;
    boolean redAlliance = false;
    public static DriveModes driveMode = DriveModes.PID;
    
    public static boolean selectedCamera = false;
    
    public static OI oi;
    public static Bling bling;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Launcher launcher;
    public static Collector collector;
    public static Climber climber;
    public static LEDs lEDs;
    public static Agitator agitator;
    public static Conveyor conveyor;
    public static GearCollector gearCollector;
    public static CameraSwitcher cameraSwitcher;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**Adding sendable choosers**/
    public static SendableChooser<CommandGroup> autonomousChooser;
    public static SendableChooser<Boolean> allianceChooser;

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
        conveyor = new Conveyor();
        gearCollector = new GearCollector();
        cameraSwitcher = new CameraSwitcher();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        
        //Instantiating Bling Class for smartbling on Robot.
        bling = new Bling();
        bling.sendRobotInit();
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        //Resets gyro
        RobotMap.headingGyro.reset();
        //Calibrates the gyro
        RobotMap.headingGyro.calibrate();
        
        /**Autonomous chooser**/
        autonomousChooser = new SendableChooser<CommandGroup>();
        autonomousChooser.addDefault("Do Nothing", null);
        autonomousChooser.addObject("Blue Gear 1", new AutonomousBlueGear1());
        autonomousChooser.addObject("Blue Gear 2", new AutonomousBlueGear2());
        autonomousChooser.addObject("Blue Gear 3", new AutonomousBlueGear3());
        //autonomousChooser.addObject("Blue Gear 1 Fuel", new AutonomousBlueGear1Fuel());
        //autonomousChooser.addObject("Blue Gear 2 Fuel", new AutonomousBlueGear2Fuel());
        //autonomousChooser.addObject("Blue Gear 3 Fuel", new AutonomousBlueGear3Fuel());
        autonomousChooser.addObject("Red Gear 1", new AutonomousRedGear1());
        autonomousChooser.addObject("Red Gear 2", new AutonomousRedGear2());
        autonomousChooser.addObject("Red Gear 3", new AutonomousRedGear3());
        //autonomousChooser.addObject("Red Gear 1 Fuel", new AutonomousRedGear1Fuel());
        //autonomousChooser.addObject("Red Gear 2 Fuel", new AutonomousRedGear2Fuel());
        //autonomousChooser.addObject("Red Gear 3 Fuel", new AutonomousRedGear3Fuel());
        SmartDashboard.putData("Autonomous Chooser", autonomousChooser);
        
        /**Alliance chooser**/
        allianceChooser = new SendableChooser<Boolean>();
        allianceChooser.addDefault("No Alliance", null);
        allianceChooser.addObject("Blue Alliance", blueAlliance = true);
        allianceChooser.addObject("Red Alliance", redAlliance = true);
        SmartDashboard.putData("Alliance Chooser", allianceChooser);
        
        /**Display what's on which side**/
        if (blueAlliance == true) {
        	SmartDashboard.putString("Right Side", "Human Player Station");
        	SmartDashboard.putString("Left Side", "Boiler");
        	redAlliance = false;
        } else if (redAlliance == true) {
        	SmartDashboard.putString("Right Side", "Boiler");
        	SmartDashboard.putString("Left Side", "Human Player Station");
        	blueAlliance = false;
        }
        
        /** Instantiate a the camera server for both USB webcams in a separate thread **/
        Thread cameraThread = new Thread(() -> {        	
            // 640, 480
            // 320, 240
            // 160, 120
      
        	
            UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);            
            camera1.setResolution(320, 240);
            camera1.setFPS(20);
            
            UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
            camera2.setResolution(320, 240);
            camera2.setFPS(20);
            
            CvSink cvSink = CameraServer.getInstance().getVideo(camera1);
            CvSource outputStream = CameraServer.getInstance().putVideo("Video", 320, 240);
            Mat source = new Mat();     

            boolean currentCamera = selectedCamera;
            while( !Thread.interrupted() ) {
            	// We support two cameras, so the selectedCamera is a boolean to toggle
            	// between camera1 and camera2
            	if ( currentCamera != selectedCamera ) {
            		currentCamera = selectedCamera;
	            	if ( selectedCamera == false ) {
	            		// Set the source to camera1
	            		cvSink.setSource(camera1);            		
	                	SmartDashboard.putString("Camera", "Camera 1");
	            	} else {
	            		// Set the source to camera2
	            		cvSink.setSource(camera2);
	                	SmartDashboard.putString("Camera", "Camera 2");
	            	}
            	}
 
            	//Grab image from the source camera
            	cvSink.grabFrame(source);
            	
            	// if there was an image collected, then send it to the dashboard via
            	// the output stream
            	if ( source.empty() == false ) {
            		outputStream.putFrame(source);
            	}
            }
        });
        
        cameraThread.start();
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	//Notify the Driver and operator that the match has finished
    		Robot.oi.driverControl.rumbleTimeRep(1, 200, 2);
        	Robot.oi.operatorControl.rumbleTimeRep(1, 200, 2);
    }
    
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = autonomousChooser.getSelected();
    	//autonomousCommand = new AutonomousBlueGear3();
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
        Robot.oi.driverControl.rumbleTimeRep(1, 100, 3);
        Robot.oi.operatorControl.rumbleTimeRep(1, 100, 3);
        
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        Thread timerThread = new Thread(() -> {
         	//HardwareTimer matchTimer = new HardwareTimer();
         	//matchTimer.delay(120);
    		 System.out.println("PrintRRRRRRRRRRRR");
         	try {
 				Thread.sleep(120000);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         	//}catch(InterruptedException e)
         	System.out.println("PrintYYYYYYYYYYYYYYYYYY");
         	bling.sendEndgame();
         	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         	System.out.println("PrintXXXXXXXXXXX");
         	
         	//matchTimer.delay(3);
         	bling.sendOff();
         });
         timerThread.start();

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
