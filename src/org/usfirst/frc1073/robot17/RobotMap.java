package org.usfirst.frc1073.robot17;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static Servo panny;
    public static Servo tilty;
    
    public static AnalogInput pannyA;
    public static AnalogInput tiltyA;
    
    public static boolean leftLimit; // Impromptu "limit switches" based off of the angle of the servos
    public static boolean rightLimit; 
    public static boolean lowerLimit;
    public static boolean upperLimit;
    
    public static double lowP; // Limit values
    public static double highP;
    public static double lowT; // Limit values
    public static double highT;
    
    public static void init() {
    	
    	// Servo limit value initialization
    	lowP = 0;
    	highP = 10;
    	
    	lowT = 0;
    	highT = 10;
    	
    	panny = new Servo(0);
        LiveWindow.addActuator("DriveTrain", "panny", panny);
        
        pannyA = new AnalogInput(0);
        
        tilty = new Servo(1);
        LiveWindow.addActuator("DriveTrain", "tilty", tilty);
        
        tiltyA = new AnalogInput(1);
        
        
    }
}
