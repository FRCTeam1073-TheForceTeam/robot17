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

import org.usfirst.frc1073.robot17.RobotMap;
import org.usfirst.frc1073.robot17.commands.*;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	/**Manually adding CANTalons**/
private final CANTalon leftMotor1 = RobotMap.pIDDriveleftMotor1;
    private final CANTalon leftMotor2 = RobotMap.pIDDriveleftMotor2;
    private final CANTalon leftMotor3 = RobotMap.pIDDriveleftMotor3;
    private final CANTalon rightMotor1 = RobotMap.pIDDriverightMotor1;
    private final CANTalon rightMotor2 = RobotMap.pIDDriverightMotor2;
    private final CANTalon rightMotor3 = RobotMap.pIDDriverightMotor3;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private final double DEADZONE_VALUE = .05;
    private final double WHEEL_RADIUS = 4.0;
    private final double CUBIC_SCALE = .07;
    
    /**Deadzone code:
     * Takes in joystick value
     * If it falls between 0 and <code>deadzone</code> 
     * set it to 0
    **/
    
    public double deadzone(double input,double deadzoneVal)
    {
    	if(input <= deadzoneVal && input > 0) input = 0;
    	
    	if(input >= -deadzoneVal && input < 0) input = 0;
    	
    	return input;
    }
    
    public double cubicScale(double in)
    {
    	return CUBIC_SCALE*in + (1 - CUBIC_SCALE) * Math.pow(in, 3);
    }
    
    /**Basic drive code:
     * 	-Makes sure left side is inverted and right side isn't
     * 	-Sets non-Motor1 motors as followers
     * 	-Sets non-Motor1 motors to follow their respective side's Motor1
     * 	-Sets Motor1 motors to PercentVbus
     * 	-Sets Motor1 motors to double left and double right, respectively
     **/
    
    public void basicDrive(double left, double right) {
    	leftMotor1.setInverted(true);
    	leftMotor2.setInverted(true);
    	leftMotor3.setInverted(true);
    	rightMotor1.setInverted(false);
    	rightMotor2.setInverted(false);
    	rightMotor3.setInverted(false);
    	
    	leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	
    	leftMotor2.set(leftMotor1.getDeviceID());
    	leftMotor3.set(leftMotor1.getDeviceID());
    	rightMotor2.set(rightMotor1.getDeviceID());
    	rightMotor3.set(rightMotor1.getDeviceID());
    	
    	leftMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	rightMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	
    	rightMotor1.set(right);
    	leftMotor1.set(left);
    }
    
    public void cubicDrive(double left, double right) {
    	leftMotor1.setInverted(true);
    	leftMotor2.setInverted(true);
    	leftMotor3.setInverted(true);
    	rightMotor1.setInverted(false);
    	rightMotor2.setInverted(false);
    	rightMotor3.setInverted(false);
    	
    	leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	
    	leftMotor2.set(leftMotor1.getDeviceID());
    	leftMotor3.set(leftMotor1.getDeviceID());
    	rightMotor2.set(rightMotor1.getDeviceID());
    	rightMotor3.set(rightMotor1.getDeviceID());
    	
    	leftMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	rightMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	
    	rightMotor1.set(cubicScale(deadzone(right,DEADZONE_VALUE)));
    	leftMotor1.set(cubicScale(deadzone(left,DEADZONE_VALUE)));
    }
    
    public void arcadeDrive(double left, double right) {
    	leftMotor1.setInverted(true);
    	leftMotor2.setInverted(true);
    	leftMotor3.setInverted(true);
    	rightMotor1.setInverted(false);
    	rightMotor2.setInverted(false);
    	rightMotor3.setInverted(false);
    	
    	leftMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightMotor3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	
    	leftMotor2.set(leftMotor1.getDeviceID());
    	leftMotor3.set(leftMotor1.getDeviceID());
    	rightMotor2.set(rightMotor1.getDeviceID());
    	rightMotor3.set(rightMotor1.getDeviceID());
    	
    	leftMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	rightMotor1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	
    	double tempLeft = cubicScale(deadzone(left,DEADZONE_VALUE)-deadzone(left,DEADZONE_VALUE));
    	double tempRight = cubicScale(deadzone(left,DEADZONE_VALUE)+deadzone(left,DEADZONE_VALUE));
    	double difL,difR;
    	
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
    	
    	leftMotor1.set(tempLeft);
    	rightMotor1.set(tempRight);
    }
    
    /**Left/right raw speed, distance, etc.:
     * getSpeed gives the speed
     * getPosition measures the radians passed
     */
    public double getLeftRawSpeed() {
    	return leftMotor1.getSpeed();
    }
    
    public double getLeftRawDistance() {
    	return leftMotor1.getPosition();
    }
    
    public double getLeftDistanceInches() {
    	return getLeftRawDistance() * (WHEEL_RADIUS * 2 * Math.PI);
    }
    
    public double getRightRawSpeed() {
    	return rightMotor1.getSpeed();
    }
    
    public double getRightRawDistance() {
    	return rightMotor1.getPosition();
    }
    
    public double getRightDistanceInches() {
    	return getRightRawDistance() * (WHEEL_RADIUS * 2 * Math.PI);
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

