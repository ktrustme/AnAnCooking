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
	NO_MODEL_NAME(1, "No model name specified. Call fix1()"),
	WRONG_INPUT_FORMAT(2, "Wrong input format! Call fix2()."),
	NO_OPTION_SET_DATA(4, "No optionset info specified! Call fix4()."),
	NO_OPTION_DATA(5,"No option info specified! Call fix5()."),
	NO_PRICING_INFO(3, "No pricing infomation! Call fix3()."),
	CANT_FIND_OPTIONSET(6, "Can't find specified option set! Please re-check the name!");
	
	
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
