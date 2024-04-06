package com.qspiders.busreservation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qspiders.busreservation.busrepository.BookingRepo;
import com.qspiders.busreservation.dto.Bookings;

@Repository
public class CustomerDao {
	@Autowired
	BookingRepo bookingRepo;

	public Bookings getBookings(int bookingNo) {
		Bookings bookings = bookingRepo.findByBookingNo(bookingNo);
		return bookings;
	}

	public List<Bookings> getBookingHistory(int userId) {
		Optional<List<Bookings>> optional = bookingRepo.findByUserId(userId);
		return optional.get();
	}

}
