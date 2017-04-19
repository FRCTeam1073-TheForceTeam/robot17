package org.usfirst.frc1073.robot17;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
	//Constructor
	// open file
	public Logger(){
		
	}
	
// just output user string with time and function name
	public static void setLog(String content) {
		String matchortime = Robot.robotPreferences.getString("match", Robot.starttime);
		String timeStamp = new SimpleDateFormat("HH:mm:ss:ms").format(Calendar.getInstance().getTime());
		String nameOfClass = Thread.currentThread().getStackTrace()[2].getClassName();
		String nameOfFunction = Thread.currentThread().getStackTrace()[2].getMethodName();
		String fileName = "/home/lvuser/Logger" + matchortime + ".txt";
		
		try(FileWriter fw = new FileWriter(fileName, true)){
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw);
			    fw.write(timeStamp + "::" + nameOfClass + "." + nameOfFunction + ": " + content + "\n");
			    fw.close();
			} catch (IOException e) {}
		
	}	
}