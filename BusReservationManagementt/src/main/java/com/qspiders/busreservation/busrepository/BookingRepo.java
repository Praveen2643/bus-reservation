package com.qspiders.busreservation.busrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qspiders.busreservation.dto.Bookings;

public interface BookingRepo extends JpaRepository<Bookings, Integer> {

	Bookings findByBookingNo(int bookingNo);

	Optional<List<Bookings>> findByUserId(int userId);
}
