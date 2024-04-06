package com.qspiders.busreservation.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qspiders.busreservation.busrepository.BusRepo;
import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Seats;
import com.qspiders.busreservation.exceptionHandler.IdNotFoundException;
import com.qspiders.busreservation.exceptionHandler.TimeNotFoundException;

import jakarta.transaction.Transactional;

@Repository
public class BusDao {
	@Autowired
	BusRepo busRepo;

	public Bus save(Bus bus) {
		Bus buss = busRepo.save(bus);
		return buss;
	}

	public Bus getByBusId(int busId) {
		Optional<Bus> optional = busRepo.findByBusId(busId);
		if (!optional.isEmpty()) {
			return optional.get();
		}
		throw new IdNotFoundException("id is wrong retry");
	}

	public List<Bus> getByTimeArrivalDepature(String arrival, String depature, String date, String time2) {
		Optional<List<Bus>> listt = busRepo.findByArrivalDeparture(arrival, depature, date, time2);
		if (!listt.isEmpty()) {
			return listt.get();
		}
		throw new TimeNotFoundException("Check in another time");
	}

	
}
