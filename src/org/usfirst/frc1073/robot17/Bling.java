package org.usfirst.frc1073.robot17;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Bling {
	NetworkTable newtable;
	
	//Instance Variables
	private String color;
	private String speed;
	private int min;
	private int max;
	private String pattern;
	private String segment;
	private String put;
	private String put2;
	
	//Constructor
	public Bling() {
		newtable = NetworkTable.getTable("Bling");
		//NetworkTable.initialize();
	}
	
	//Methods
	public String setPattern(String patt, String col, String seg, String spd, int mini, int maxi) {
		pattern = patt;
		color = col;
		segment = seg;
		speed = spd;
		min = mini;
		max = maxi;
		put = "Pattern=" + pattern + "," + "Color=" + color + "," + "Segment=" + segment + "," + "Speed=" + speed + "," + "Min=" + min + "," + "Max=" + max; 
		return put;
	}
	
	public void disableLeds() {
		put = "Pattern=" + "off" + "," + "Color=" + color + "," + "Segment=" + segment + "," + "Speed=" + speed + "," + "Min=" + min + "," + "Max=" + max; 
	}
	
	public void send() {
		newtable.putString("command", put);
	}

    /** Set of API functions that can be called from the robot code to send and
        pattern to the bling **/
    public void sendPattern( BlingMode pattern ) {
        Boolean validPattern = true;

        switch( pattern ) {
            case ROBOT_INIT:
		        setPattern("RainbowHalves", "rainbow", "all", "medium", 0, 100);
                break;
            case ROBOT_ERROR:
		        setPattern("Alternates", "christmas", "all", "medium", 0, 100);
                break;
            case CLIMBING:
                setPattern("ColorFade", "teamcolors", "all", "fast", 0, 100);
                break;

            // put all pattern specifiers above this point
            case OFF:
                disableLeds();
                break;
            default:
                setPattern("Error", "red", "all", "fast", 0, 100);
                break;
        }
        
        if ( validPattern ) {
            send();
        }

        return;
    }

	public void sendClimbing() {
		setPattern("ColorFade", "teamcolors", "all", "fast", 0, 100);
		SmartDashboard.putString("Current Bling", "Climbing");
		send();
	}

	public void sendFinished() {
		setPattern("RainbowHalves", "red", "all", "fast", 0, 100);
		SmartDashboard.putString("Current Bling", "Finished");
		send();
	}
			
	public void sendFinishedClimbing() {
	    setPattern("Alternates", "teamcolors", "all", "medium", 0, 100);
	    SmartDashboard.putString("Current Bling", "Finished Climbing");
		send();
	}
	
	public void sendBackup() {
		setPattern("blinking", "yellow", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "Backup");
		send();
	}
	
	public void sendEnd() {
		setPattern("fireflies", "rainbow", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "End (Rainbow Blinking)");
		send();
	}
	
	public void sendOff() {
		disableLeds();
		SmartDashboard.putString("Current Bling", "LEDs Disabled");
		send();
	}
	
	public void sendRobotInit() {
		setPattern("RainbowHalves", "rainbow", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "RobotInit (Rainbow)");
		send();
	}
	
	public void sendAutoDrive() {
		setPattern("ColorChase", "red", "all", "slow", 0, 100);
		SmartDashboard.putString("Current Bling", "AutoDrive (Color Chase Red)");
		send();
	}
	
	
	public void sendAutoDone() {
		setPattern("solid", "red", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "AutoDriveDone (Solid Red)");
		send();
	}
	
	public void sendRemoveGear() {
		setPattern("solid", "green", "all", "slow", 0, 100);
		SmartDashboard.putString("Current Bling", "RemoveGear (Solid Green)");
		send();
	}
	
	public void sendEndMovePID() {
		setPattern("solid", "red", "right", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "EndMovePID (Solid Red)");
		send();
	}
	
	public void sendEndMoveAway() {
		setPattern("solid", "purple", "right", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "EndMoveAway (Solid Purple)");
		send();
	}
	
	public void sendAutoTurnRight() {
		setPattern("blinking", "Green", "right", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "AutoTurnRight (Blinking Green)");
		send();
	}
	
	public void sendAutoTurnLeft() {
		setPattern("blinking", "Green", "left", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "AutoTurnLeft (Blinking Green)");
		send();
	}
	
	public void sendPegTargeting() {
		setPattern("scanner", "Green", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "GearPeg (Scanning Green)");
		send();
	}
	
	public void sendMoveAway() {
		setPattern("blinking", "purple", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "MoveAway (Blinking Purple)");
		send();
	}
	
	public void sendBoilerTargeting() {
		setPattern("scanner", "purple", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "BoilerTargeting (Scanning Purple)");
		send();
	}
	
	public void sendFuelLaunching() {
		setPattern("RainbowHalves", "rainbow", "all", "fast", 0, 100);
		SmartDashboard.putString("Current Bling", "LaunchingFuel (Rainbow)");
		send();
	}
	
	public void sendCollectBall()
	{
		setPattern("solid", "blue", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "CollectBall (Solid Blue)");
		send();
	}
	
	public void sendPurgeBall()
	{
		setPattern("solid", "orange", "all", "medium", 0, 100);
		SmartDashboard.putString("Current Bling", "CollectBall (Solid Orange)");
		send();
	}
	
	public void sendEndgame() {
		setPattern("blinking", "yellow", "all", "fast", 0, 100);
		SmartDashboard.putString("Current Bling", "EndGame (Blinking Yellow)");
		send();
	}
}