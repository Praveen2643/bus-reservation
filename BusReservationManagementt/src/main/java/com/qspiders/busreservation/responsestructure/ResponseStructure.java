package com.qspiders.busreservation.responsestructure;

import java.time.LocalDateTime;


import org.springframework.stereotype.Component;

import com.qspiders.busreservation.dto.Bus;

import lombok.Data;
@Data
@Component
public class ResponseStructure<T> {
	private Object data;
	private LocalDateTime dateTime;
	private int statuscode;
	private String message;
}
