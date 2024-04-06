package com.qspiders.busreservation.exceptionHandler;

public class IdNotFoundException extends RuntimeException {
	public IdNotFoundException(String string) {
		super(string);
	}
}
