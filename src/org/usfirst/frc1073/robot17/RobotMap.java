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
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

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
//    public static AnalogGyro driveTrainHeadingGyro;
    public static AnalogInput driveTrainProximitySensorFront;
    public static AnalogInput driveTrainProximitySensorBack;
    public static SpeedController collectorcollectorMotor;
    public static Relay lEDsspike1;
    public static Relay lEDsspike2;
    public static SpeedController conveyorconveyorMotor;
    public static DigitalInput gearCollectorgearLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**Manually adding CANTalons**/
    public static CANTalon driveTrainleftMotor1;
    public static CANTalon driveTrainleftMotor2;
    public static CANTalon driveTrainleftMotor3;
    public static CANTalon driveTrainrightMotor1;
    public static CANTalon driveTrainrightMotor2;
    public static CANTalon driveTrainrightMotor3;
    public static CANTalon launcherlauncherMotor1;
    public static CANTalon climberclimber;
    
    public static ADXRS450_Gyro headingGyro;

    public static void init() {
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainleftEnc = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "leftEnc", driveTrainleftEnc);
        driveTrainleftEnc.setDistancePerPulse(1.0);
        driveTrainleftEnc.setPIDSourceType(PIDSourceType.kRate);
        driveTrainrightEnc = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "rightEnc", driveTrainrightEnc);
        driveTrainrightEnc.setDistancePerPulse(1.0);
        driveTrainrightEnc.setPIDSourceType(PIDSourceType.kRate);
//        driveTrainHeadingGyro = new AnalogGyro(0);
//        LiveWindow.addSensor("DriveTrain", "HeadingGyro", driveTrainHeadingGyro);
//        driveTrainHeadingGyro.setSensitivity(0.007);
        
        headingGyro = new ADXRS450_Gyro();
        LiveWindow.addSensor("DriveTrain", "headingGyro", headingGyro);
        
        driveTrainProximitySensorFront = new AnalogInput(2);
        LiveWindow.addSensor("DriveTrain", "ProximitySensorFront", driveTrainProximitySensorFront);
        
        driveTrainProximitySensorBack = new AnalogInput(3);
        LiveWindow.addSensor("DriveTrain", "ProximitySensorBack", driveTrainProximitySensorBack);
        
        collectorcollectorMotor = new Victor(8);
        LiveWindow.addActuator("Collector", "collectorMotor", (Victor) collectorcollectorMotor);
        
        lEDsspike1 = new Relay(0);
        LiveWindow.addActuator("LEDs", "spike1", lEDsspike1);
        
        lEDsspike2 = new Relay(1);
        LiveWindow.addActuator("LEDs", "spike2", lEDsspike2);
        
        conveyorconveyorMotor = new Victor(9);
        LiveWindow.addActuator("Conveyor", "conveyorMotor", (Victor) conveyorconveyorMotor);
        
        gearCollectorgearLimit = new DigitalInput(4);
        LiveWindow.addSensor("GearCollector", "gearLimit", gearCollectorgearLimit);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        /**Manually adding CANTalons**/
        driveTrainrightMotor1 = new CANTalon(1);
        LiveWindow.addActuator("DriveTrain", "rightMotor1", driveTrainrightMotor1);
        
        driveTrainrightMotor2 = new CANTalon(2);
        LiveWindow.addActuator("DriveTrain", "rightMotor2", driveTrainrightMotor2);
        
        climberclimber = new CANTalon(3);
        LiveWindow.addActuator("Climber", "climber2", climberclimber);
        
        launcherlauncherMotor1 = new CANTalon(4);
        LiveWindow.addActuator("Launcher", "launcherMotor1", launcherlauncherMotor1);
        
        driveTrainrightMotor3 = new CANTalon(5);
        LiveWindow.addActuator("DriveTrain", "rightMotor3", driveTrainrightMotor3);
        
    	driveTrainleftMotor1 = new CANTalon(6);
        LiveWindow.addActuator("DriveTrain", "leftMotor1", driveTrainleftMotor1);
        
        driveTrainleftMotor2 = new CANTalon(7);
        LiveWindow.addActuator("DriveTrain", "leftMotor2", driveTrainleftMotor2);
        
        driveTrainleftMotor3 = new CANTalon(8);
        LiveWindow.addActuator("DriveTrain", "leftMotor3", driveTrainleftMotor3);
        
    }
    
}
