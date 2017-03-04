// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1073.robot17.subsystems;

import org.usfirst.frc1073.robot17.Logger;
import org.usfirst.frc1073.robot17.Robot;
import org.usfirst.frc1073.robot17.RobotMap;
import org.usfirst.frc1073.robot17.commands.*;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	
	//TODO remove when actually added in robotbuilder
	private final ADXRS450_Gyro headingGyro = RobotMap.headingGyro;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Encoder leftEnc = RobotMap.driveTrainleftEnc;
    private final Encoder rightEnc = RobotMap.driveTrainrightEnc;
    //private final AnalogGyro headingGyro = RobotMap.driveTrainHeadingGyro;
    private final AnalogInput proximitySensorFront = RobotMap.driveTrainProximitySensorFront;
    private final AnalogInput proximitySensorBack = RobotMap.driveTrainProximitySensorBack;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**default inversion booleans**/
    private boolean leftInverted = true;
    private boolean rightInverted = false;

	/**Manually adding CANTalons**/
    private final CANTalon leftMotor1 = RobotMap.driveTrainleftMotor1;
    private final CANTalon leftMotor2 = RobotMap.driveTrainleftMotor2;
    private final CANTalon leftMotor3 = RobotMap.driveTrainleftMotor3;
    private final CANTalon rightMotor1 = RobotMap.driveTrainrightMotor1;
    private final CANTalon rightMotor2 = RobotMap.driveTrainrightMotor2;
    private final CANTalon rightMotor3 = RobotMap.driveTrainrightMotor3;
  
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private final double DEADZONE_VALUE = .05;
    private final double WHEEL_DIA = 3.9;
    private final double CUBIC_SCALE = .02;
    private final double CUB = CUBIC_SCALE/2;
    /**Deadzone code:
     * Takes in joystick value
     * If it falls between 0 and <code>deadzone</code> 
     * set it to 0
    **/
    
    public DriveTrain() {
    	if(Robot.isOrientationSwitched == true) {
    		leftInverted = false;
    		rightInverted = true;
    	} else if(Robot.isOrientationSwitched == false) {
    		leftInverted = true;
    		rightInverted = false;
    	}
    	
    	leftMotor1.setInverted(leftInverted);
    	leftMotor2.setInverted(leftInverted);
    	leftMotor3.setInverted(leftInverted);
    	rightMotor1.setInverted(rightInverted);
    	rightMotor2.setInverted(rightInverted);
    	rightMotor3.setInverted(rightInverted);
    	
    	leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftMotor1.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor1.changeControlMode(CANTalon.TalonControlMode.Follower);
    	
    	leftMotor2.set(leftMotor3.getDeviceID());
    	leftMotor1.set(leftMotor3.getDeviceID());
    	rightMotor2.set(rightMotor3.getDeviceID());
    	rightMotor1.set(rightMotor3.getDeviceID());
    	
    	leftMotor3.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	rightMotor3.changeControlMode(CANTalon.TalonControlMode.PercentVbus); 
    	
    	leftMotor3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        rightMotor3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        	
        leftMotor3.configNominalOutputVoltage(+0f, -0f);
        rightMotor3.configNominalOutputVoltage(+0f, -0f);
        	
        leftMotor3.configPeakOutputVoltage(+12.0f, -12.0f);
        rightMotor3.configPeakOutputVoltage(+12.0f, -12.0f);
        
        leftMotor3.configEncoderCodesPerRev(360);
        rightMotor3.configEncoderCodesPerRev(360);
    }
    
    public double deadzone(double input,double deadzoneVal)
    {
    	if(input <= deadzoneVal && input > 0) input = 0;
    	
    	if(input >= -deadzoneVal && input < 0) input = 0;
    	
    	return input;
    }
    
    public double cubicScale(double in, double cub)
    {
    	return cub*in + (1 - cub) * Math.pow(in, 3);
    }
    
    /**Basic drive code:
     * 	-Makes sure left side is inverted and right side isn't
     * 	-Sets non-Motor1 motors as followers
     * 	-Sets non-Motor1 motors to follow their respective side's Motor1
     * 	-Sets Motor1 motors to PercentVbus
     * 	-Sets Motor1 motors to double left and double right, respectively
     **/
    
    public void basicDrive(double left, double right) {
    	
        leftMotor3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        rightMotor3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        	
        leftMotor3.configNominalOutputVoltage(+0f, -0f);
        rightMotor3.configNominalOutputVoltage(+0f, -0f);
        	
        leftMotor3.configPeakOutputVoltage(+12.0f, -12.0f);
        rightMotor3.configPeakOutputVoltage(+12.0f, -12.0f);
            	
    	rightMotor3.set(right);
    	leftMotor3.set(left);
    }
    
    public void cubicDrive(double left, double right) {
         
    	rightMotor3.set(cubicScale(deadzone(right,DEADZONE_VALUE),CUBIC_SCALE));
    	leftMotor3.set(cubicScale(deadzone(left,DEADZONE_VALUE),CUBIC_SCALE));
    }
    
    public void arcadeDrive(double left, double right) {
         
    	double tempLeft = cubicScale(deadzone(left,DEADZONE_VALUE),CUBIC_SCALE)-cubicScale(deadzone(right,DEADZONE_VALUE*3),CUB);
    	double tempRight = cubicScale(deadzone(left,DEADZONE_VALUE),CUBIC_SCALE)+cubicScale(deadzone(right,DEADZONE_VALUE*3),CUB);
    	double difL,difR = 0;
    	
    	if(tempLeft > 1)
    	{
    		difL = tempLeft-1;
    		tempRight -= difL;
    		tempLeft = 1;
    	}
    	if(tempRight > 1)
    	{
    		difR = tempRight-1;
    		tempLeft -= difR;
    		tempRight = 1;
    	}
    	if(tempLeft < -1)
    	{
    		difL = tempLeft+1;
    		tempRight -= difL;
    		tempLeft = -1;
    	}
    	if(tempRight < -1)
    	{
    		difR = tempRight+1;
    		tempLeft -= difR;
    		tempRight = -1;
    	}
    	
    	Logger.setLog("tempLeft: "+Double.toString(tempLeft));
    	Logger.setLog("tempRight: "+Double.toString(tempRight));
    	
    	//Robot.oi.driverControl.rumble(Math.abs(tempLeft));
    	leftMotor3.set(-1*tempLeft);
    	rightMotor3.set(-1*tempRight);
    }
    
    /**Left/right raw speed, distance, etc.:
     * getSpeed gives the speed
     * getPosition measures the radians passed
     */
    public double getLeftRawSpeed() {
    	return leftMotor3.getSpeed();
    }
    
    public double getLeftRawDistance() {
    	return leftMotor3.getPosition();
    }
    
    public double getLeftDistanceInches() {
    	return getLeftRawDistance() * (WHEEL_DIA * Math.PI);
    }
    
    public double getRightRawSpeed() {
    	return rightMotor3.getSpeed();
    }
    
    public double getRightRawDistance() {
    	return rightMotor3.getPosition();
    }
    
    public double getRightDistanceInches() {
    	return getRightRawDistance() * (WHEEL_DIA * Math.PI);
    }
    
    public void setLeftPos(double pos)
    {
    	leftMotor3.setPosition(pos);
    }
    
    public void setRightPos(double pos)
    {
    	rightMotor3.setPosition(pos);
    }
    
    public double getLeftPos()
    {
    	return leftMotor3.getEncPosition();
    }

    public double getRightPos()
    {
    	return rightMotor3.getEncPosition();
    }
    
    public void resetGyro()
    {
    	headingGyro.reset();
    }
    
    public double getDegrees()
    {
    	return headingGyro.getAngle();
    	
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

