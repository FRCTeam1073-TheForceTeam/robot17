package org.usfirst.frc1073.robot17;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
	public static void setLog(String content) {

		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		String nameOfClass = Thread.currentThread().getStackTrace()[2].getClassName();
		
		try(FileWriter fw = new FileWriter("Logger.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(timeStamp + "::" + nameOfClass + ": " + content + "\n");
			} catch (IOException e) {}
		
	}
	public static void clear(String FileName){
		PrintWriter writer;
		try {
			writer = new PrintWriter(FileName);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
}