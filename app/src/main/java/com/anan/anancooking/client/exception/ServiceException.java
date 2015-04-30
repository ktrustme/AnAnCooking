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

import android.app.Activity;

/**
 * @author kuoxin
 * Customized exception class. Different type of exceptions distinguished
 * by different enumeration type given to the constructor.
 */
public class ServiceException extends Exception implements ExceptionHandlerInterface{
    //Activity parentActivity;
	private MyExceptionEnum exenum;
	public ServiceException(MyExceptionEnum exenum){
        //this.parentActivity = parentActivity;
		this.exenum = exenum;
	}

	public MyExceptionEnum getExceptionEnum(){
		return this.exenum;
	}

    @Override
    public void fix(Activity parentActivity) {
        Fixer fixer = new Fixer(parentActivity);
        String message = exenum.getMessage();
        int errno = exenum.getCode();

        message = "Error detected: " + message;

        switch(errno){
            case 1: fixer.fix1(exenum);
                break;
            case 2: fixer.fix2(exenum);
                break;
            case 3: fixer.fix3(exenum);
                break;
            default: message = "Oh no! An exception occured but I don't know how to handle this!";
        }

    }
}
