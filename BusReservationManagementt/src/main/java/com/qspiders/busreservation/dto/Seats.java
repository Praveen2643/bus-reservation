package com.qspiders.busreservation.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Seats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int busId;
	private int userId;
	private int seatNo;
	private int bookingNo;
	private String passengerName;
	private String status;
	}
