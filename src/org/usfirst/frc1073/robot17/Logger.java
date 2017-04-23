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
		Boolean log = true;
		String matchType = "Error";
		int matchNumber = 0;
		//String match = Robot.robotPreferences.getString("match", Robot.starttime);
		if(OI.matchType == "Practice")
		{
			log = true;
			matchType = "Practice";
			//matchNumber = OI.practiceMatch;
		}
		else if (OI.matchType == "Competitive")
		{
			log = true;
			matchType = "Competitive";
			//matchNumber = OI.competitiveMatch;
		}
		else
		{
			log = false;
		}
		if (log == true)
		{
			String comp = Robot.robotPreferences.getString("Competition", "Worlds2017");
			String timeStamp = new SimpleDateFormat("HH:mm:ss:ms").format(Calendar.getInstance().getTime());
			String timeStamp1 = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			String nameOfClass = Thread.currentThread().getStackTrace()[2].getClassName();
			String nameOfFunction = Thread.currentThread().getStackTrace()[2].getMethodName();
			String fileName = "/home/lvuser/Logger"+ timeStamp1 + comp + matchType + ".txt";
			
			try(FileWriter fw = new FileWriter(fileName, true)){
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw);
				    fw.write(timeStamp + "::" + nameOfClass + "." + nameOfFunction + ": " + content + "\n");
				    fw.close();
				} catch (IOException e) {}
			}
				
	}	
}