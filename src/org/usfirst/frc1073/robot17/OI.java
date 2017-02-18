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

import org.usfirst.frc1073.robot17.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton driveOrientationToggleBut;
    public JoystickButton cameraSwitchBut;
    public JoystickButton gearAlignBut;
    public JoystickButton launchAlignBut;
    public Joystick driverControl;
    public JoystickButton agitateBut;
    public JoystickButton climbBut;
    public JoystickButton purgeBut;
    public JoystickButton collectBut;
    public Joystick operatorControl;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operatorControl = new Joystick(1);
        
        collectBut = new JoystickButton(operatorControl, 6);
        collectBut.whileHeld(new CollectFuel());
        purgeBut = new JoystickButton(operatorControl, 5);
        purgeBut.whileHeld(new PurgeFuel());
        climbBut = new JoystickButton(operatorControl, 3);
        climbBut.whileHeld(new Climb(0));
        agitateBut = new JoystickButton(operatorControl, 2);
        agitateBut.whileHeld(new Agitate());
        driverControl = new Joystick(0);
        
        launchAlignBut = new JoystickButton(driverControl, 1);
        launchAlignBut.whenPressed(new LaunchAlign());
        gearAlignBut = new JoystickButton(driverControl, 2);
        gearAlignBut.whenPressed(new DriveToGearPeg());
        cameraSwitchBut = new JoystickButton(driverControl, 10);
        cameraSwitchBut.whenPressed(new CameraSwitch());
        driveOrientationToggleBut = new JoystickButton(driverControl, 9);
        driveOrientationToggleBut.whileHeld(new DriveOrientationToggle());


        // SmartDashboard Buttons
        SmartDashboard.putData("AutoTest", new AutoTest());
        SmartDashboard.putData("DriveToWall", new DriveToWall());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getdriverControl() {
        return driverControl;
    }

    public Joystick getoperatorControl() {
        return operatorControl;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

