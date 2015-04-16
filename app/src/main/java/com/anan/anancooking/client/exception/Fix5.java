/*------------------------------
 This file: Fix5.java
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

import java.util.logging.Logger;

/**
 * @author kuoxin
 * The methods in this class are responsible for all the fixing stuff.
 * But current what those method do is just record some meesage into
 * the log file.
 */
public class Fix5 {
	private String logfile;
	public Fix5(String logfile){
		this.logfile = logfile;
	}
	public void fix1(){
		Logger logger = Logger.getLogger("Exception log");
		logger.info("fix1(): Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
	}
	
	public void fix2(){
		Logger logger = Logger.getLogger("Exception log");
		logger.info("fix2(): Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
		//System.out.println("Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
	}

	public void fix3(){
		Logger logger = Logger.getLogger("Exception log");
		logger.info("fix3(): Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
	}

	public void fix4(){
		Logger logger = Logger.getLogger("Exception log");
		logger.info("fix4(): Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
	}
	
	public void fix5(){
		Logger logger = Logger.getLogger("Exception log");
		logger.info("fix5(): Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
	}
	
	public void fix6(){
		Logger logger = Logger.getLogger("Exception log");
		logger.info("fix6(): Sry I don't know how to deal with that, but you know what? Let's just continue the program!");
	}
}
