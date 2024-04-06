package com.qspiders.busreservation.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Bookings {
	@Id
	private int bookingNo;
	private String passangerName;
	private String date;
	private String arrival;
	private String depature;
	private int seatNo;
	private int busNo;
	private int ticketPrice;
	private int busId;
	private int userId;
	private String age;
	private String couponCode;
	private int points;
}
