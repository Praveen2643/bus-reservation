package com.qspiders.busreservation.service;

import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface CustomerService {
	public ResponseStructure<?> bookTicket(Bookings bookings);

	public ResponseStructure<?> cancelTicket(int busId, int bookingNo);

	public ResponseStructure<?> viewticketpricedeductingprice(Bookings bookings);

	public ResponseStructure<?> bookingHistory(int userId);
}
