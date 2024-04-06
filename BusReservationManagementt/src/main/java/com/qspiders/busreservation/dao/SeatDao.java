package com.qspiders.busreservation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qspiders.busreservation.busrepository.SeatsRepo;
import com.qspiders.busreservation.dto.Seats;

@Repository
public class SeatDao {
	@Autowired
	SeatsRepo seatsRepo;

	public Seats getSeat(int busId, int seatNo) {
		Optional<Seats> seats = seatsRepo.findSeats(busId, seatNo);
		return seats.get();

	}

	public Seats save(Seats seat) {
		Seats seats = seatsRepo.save(seat);
		return seats;
	}
}
