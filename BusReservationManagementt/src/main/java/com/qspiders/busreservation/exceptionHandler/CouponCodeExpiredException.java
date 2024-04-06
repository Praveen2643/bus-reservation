package com.qspiders.busreservation.exceptionHandler;

public class CouponCodeExpiredException extends RuntimeException {

	public CouponCodeExpiredException(String message) {
		super(message);
	}

}
