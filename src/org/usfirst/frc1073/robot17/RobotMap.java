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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import com.ctre.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainleftEnc;
    public static Encoder driveTrainrightEnc;
    public static AnalogGyro driveTrainHeadingGyro;
    public static SpeedController launcherconveyorMotor;
    public static SpeedController collectorcollectorMotor;
    public static SpeedController climberclimber2;
    public static SpeedController climberclimber1;
    public static Relay lEDsspike1;
    public static Relay lEDsspike2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**Manually adding CANTalons**/
    public static CANTalon driveTrainleftMotor1;
    public static CANTalon driveTrainleftMotor2;
    public static CANTalon driveTrainleftMotor3;
    public static CANTalon driveTrainrightMotor1;
    public static CANTalon driveTrainrightMotor2;
    public static CANTalon driveTrainrightMotor3;
    public static CANTalon launcherlauncherMotor1;

    public static void init() {
    	
    	/**Manually adding CANTalons**/
    	driveTrainleftMotor1 = new CANTalon(1);
        LiveWindow.addActuator("DriveTrain", "leftMotor1", driveTrainleftMotor1);
        
        driveTrainleftMotor2 = new CANTalon(2);
        LiveWindow.addActuator("DriveTrain", "leftMotor2", driveTrainleftMotor2);
        
        driveTrainleftMotor3 = new CANTalon(3);
        LiveWindow.addActuator("DriveTrain", "leftMotor3", driveTrainleftMotor3);
        
        driveTrainrightMotor1 = new CANTalon(4);
        LiveWindow.addActuator("DriveTrain", "rightMotor1", driveTrainrightMotor1);
        
        driveTrainrightMotor2 = new CANTalon(5);
        LiveWindow.addActuator("DriveTrain", "rightMotor2", driveTrainrightMotor2);
        
        driveTrainrightMotor3 = new CANTalon(6);
        LiveWindow.addActuator("DriveTrain", "rightMotor3", driveTrainrightMotor3);
        
        launcherlauncherMotor1 = new CANTalon(0);
        LiveWindow.addActuator("Launcher", "launcherMotor1", launcherlauncherMotor1);
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainleftEnc = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "leftEnc", driveTrainleftEnc);
        driveTrainleftEnc.setDistancePerPulse(1.0);
        driveTrainleftEnc.setPIDSourceType(PIDSourceType.kRate);
        driveTrainrightEnc = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "rightEnc", driveTrainrightEnc);
        driveTrainrightEnc.setDistancePerPulse(1.0);
        driveTrainrightEnc.setPIDSourceType(PIDSourceType.kRate);
        driveTrainHeadingGyro = new AnalogGyro(0);
        LiveWindow.addSensor("DriveTrain", "HeadingGyro", driveTrainHeadingGyro);
        driveTrainHeadingGyro.setSensitivity(0.007);
        launcherconveyorMotor = new Victor(0);
        LiveWindow.addActuator("Launcher", "conveyorMotor", (Victor) launcherconveyorMotor);
        
        collectorcollectorMotor = new Victor(1);
        LiveWindow.addActuator("Collector", "collectorMotor", (Victor) collectorcollectorMotor);
        
        climberclimber2 = new Victor(3);
        LiveWindow.addActuator("Climber", "climber2", (Victor) climberclimber2);
        
        climberclimber1 = new Victor(2);
        LiveWindow.addActuator("Climber", "climber1", (Victor) climberclimber1);
        
        lEDsspike1 = new Relay(0);
        LiveWindow.addActuator("LEDs", "spike1", lEDsspike1);
        
        lEDsspike2 = new Relay(1);
        LiveWindow.addActuator("LEDs", "spike2", lEDsspike2);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
