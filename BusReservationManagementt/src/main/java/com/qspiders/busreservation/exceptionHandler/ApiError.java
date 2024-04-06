package com.qspiders.busreservation.exceptionHandler;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {
	private Class exceptionClass;
    private String message;
	private LocalDateTime dateTime;
}
