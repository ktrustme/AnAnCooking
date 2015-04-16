/*------------------------------
 This file: ServiceException.java
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

/**
 * @author kuoxin
 * Customized exception class. Different type of exceptions distinguished
 * by different enumeration type given to the constructor.
 */
public class ServiceException extends Exception{
	private MyExceptionEnum exenum;
	public ServiceException(MyExceptionEnum exenum){
		this.exenum = exenum;
	}
	public MyExceptionEnum getExceptionEnum(){
		return this.exenum;
	}
}
