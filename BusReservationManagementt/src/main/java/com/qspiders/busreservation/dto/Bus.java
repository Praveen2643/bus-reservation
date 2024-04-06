package com.qspiders.busreservation.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@NamedQuery(name = "Bus.isTicketAvailable", query = "SELECT b.busNo,b.arrival, b.depature, b.seatsAvailable,b.date,b.time FROM Bus b WHERE b.arrival=?1 AND b.depature =?2 AND b.date=?3 AND b.time=?4 ")

public class Bus {
	@Id
	private int busId;
	private int busNo;
	private String arrival;
	private String depature;
	private String time;
	private String date;
	private int capacity;
	private int seatsAvailable;
	private int ticketPrice;
	private String status;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Bookings> listOfBookings;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seats> listOfSeats;
	@OneToOne(cascade = CascadeType.ALL)
	private Routes routes;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review> listOfReview;
	@OneToOne(cascade = CascadeType.ALL)
	private Expenses expenses;
	
}
