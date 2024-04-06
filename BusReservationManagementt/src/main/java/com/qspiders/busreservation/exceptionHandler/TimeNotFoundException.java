package com.qspiders.busreservation.exceptionHandler;

public class TimeNotFoundException extends RuntimeException {
	public TimeNotFoundException(String string) {
		super(string);
	}
}
