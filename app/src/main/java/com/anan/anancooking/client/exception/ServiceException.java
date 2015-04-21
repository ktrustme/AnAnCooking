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
public class ServiceException extends Exception implements ExceptionHandlerInterface{
	private MyExceptionEnum exenum;
	public ServiceException(MyExceptionEnum exenum){
		this.exenum = exenum;
	}
	public MyExceptionEnum getExceptionEnum(){
		return this.exenum;
	}

    @Override
    public void fix(MyExceptionEnum exenum) {
        Fixer fixer = new Fixer();
        String message = exenum.getMessage();
        int errno = exenum.getCode();
        //java.util.Date date= new java.util.Date();
        message = "Error detected: " + message;

        switch(errno){
            case 1: fixer.fix1();
                break;
            case 2: fixer.fix2();
                break;
            case 3: fixer.fix3();
                break;
            case 4: fixer.fix4();
                break;
            case 5: fixer.fix5();
                break;
            case 6: fixer.fix6();
            default: message = "Oh no! An exception occured but I don't know how to handle this!";
        }

    }
}
