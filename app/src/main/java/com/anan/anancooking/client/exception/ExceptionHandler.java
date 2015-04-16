/*------------------------------
 This file: AutoException.java
 Programmer: Xin Kuo
 Andrew ID: xkuo
 Course/Section: 18641
 Assignment: Homework 4
 Description:	
 Last Modified: 02/7/2015
 Known Bugs: Currently none
 Compiler: Java SE 7, javac
 ------------------------------*/

package com.anan.anancooking.client.exception;
import java.sql.Timestamp;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * @author kuoxin
 * The AutoExcepiton class is responsible for fix all customerized exception
 * occured in this program. Error message and corresponding handling info
 * would be recorded in an log file.
 */
public class ExceptionHandler implements ExceptionHandlerInterface{
	private String logfile;
	private Timestamp tt;
	private Logger logger;
	
	/**
	 * @param logfile
	 * Constructor. Initialize the logger object and the logger file.
	 */
	public ExceptionHandler(String logfile){
		this.logfile = logfile;
		logger = Logger.getLogger("Exception log");
		FileHandler fileHandler = null;
		try{
			fileHandler = new FileHandler(logfile, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		fileHandler.setFormatter(new SimpleFormatter());
		fileHandler.setLevel(Level.FINER);
		logger.addHandler(fileHandler);
	}
	
	@Override
	public void fix(MyExceptionEnum exenum) {
		Fix5 f5 = new Fix5(logfile);
		String message = exenum.getMessage();
		int errno = exenum.getCode();
		//java.util.Date date= new java.util.Date();
		message = "Error detected: " + message;
		logger.warning(message);
		switch(errno){
			case 1: f5.fix1();
					break;
			case 2: f5.fix2();
					break;
			case 3: f5.fix3();
					break;
			case 4: f5.fix4();
					break;
			case 5: f5.fix5();
					break;
			case 6: f5.fix6();
			default: message = "Oh no! An exception occured but I don't know how to handle this!";
		}
	
	}
}
