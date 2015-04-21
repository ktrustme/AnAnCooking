/*------------------------------
 This file: MyExceptionEnum.java
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
 * Self-defined enumeration type, containing error code and error message, and corresponding setter and getter.
 */
public enum MyExceptionEnum implements ExceptionEnumInterface{
	EMPTY_INPUT(1, ">_< Input cannot be empty."),
	NO_DISK_SPACE(2, "X_X Opps! No enough disk space!"),
    NETWORK_DISCONNECTION(3, "X_X No network connection. Please check your setting.");
	
	
	public int code;
	public String message;
	
	private MyExceptionEnum(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	@Override
	public int getCode(){
		return code;
	}
	
	@Override
	public String getMessage(){
		return message;
	}
}
