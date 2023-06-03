package module3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggingProject {
	
	static Logger mainLogger = Logger.getLogger(LoggingProject.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
		}
		mainLogger.setLevel(Level.FINE);
		mainLogger.addHandler(new ConsoleHandler());
		mainLogger.addHandler(new MyHandler());
		
		try { 
			Handler fileHandler = new FileHandler("mainLogger.log",2000,5);
			fileHandler.setFormatter(new MyFormatter());
			
			fileHandler.setFilter(new MyFilter());
			mainLogger.addHandler(fileHandler);
			
			for(int i=0; i<5; i++){
			
				mainLogger.log(Level.WARNING, "Error Message: #"+i);
			}
			mainLogger.log(Level.CONFIG, "Config data");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
