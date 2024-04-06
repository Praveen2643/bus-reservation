package com.qspiders.busreservation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.BusDao;
import com.qspiders.busreservation.dao.SeatDao;
import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.dto.Seats;
import com.qspiders.busreservation.exceptionHandler.TimeNotFoundException;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

@Service
public class BusServiceImp implements BusServicee {
	@Autowired
	BusDao busDao;

	@Autowired
	SeatDao seatDao;

	@Autowired
	ResponseStructure<Bus> responseStructure;

	@Autowired
	ResponseStructure<Bookings> responseStructure1;

	@Autowired
	ResponseStructure<Bookings> responseStructure2;

	public ResponseStructure<Bus> save(Bus buss) {
		Bus bus = busDao.save(buss);
		LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
		map.put("busId           ", bus.getBusId());
		map.put("busNo           ", bus.getBusNo());
		map.put("arrival         ", bus.getArrival());
		map.put("depature        ", bus.getDepature());
		map.put("capacity        ", bus.getCapacity());
		map.put("date            ", bus.getDate());
		map.put("time            ", bus.getTime());
		map.put("status          ", bus.getStatus());
		map.put("seats available ", bus.getSeatsAvailable());
		map.put("ticket price    ", bus.getTicketPrice());
		if (bus.getRoutes() != null) {
			map.put("route          ", bus.getRoutes());
		}
		if (!(bus.getListOfReview()==null)) {
			map.put("review         ", bus.getListOfReview());
		}
		map.put("expenses & income", bus.getExpenses());
		responseStructure.setData(map);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("bus details saved successfully...");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

	@Override
	public ResponseStructure<Bus> getByBusId(int busId) {
		Bus bus = busDao.getByBusId(busId);
		LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
		map.put("busId           ", bus.getBusId());
		map.put("busNo           ", bus.getBusNo());
		map.put("arrival         ", bus.getArrival());
		map.put("depature        ", bus.getDepature());
		map.put("capacity        ", bus.getCapacity());
		map.put("date            ", bus.getDate());
		map.put("time            ", bus.getTime());
		map.put("status          ", bus.getStatus());
		map.put("seats available ", bus.getSeatsAvailable());
		map.put("ticket price    ", bus.getTicketPrice());
		if (bus.getRoutes() != null) {
			map.put("route          ", bus.getRoutes());
		}
		if (!(bus.getListOfReview()==null)) {
			map.put("review         ", bus.getListOfReview());
		}
		map.put("expenses & income", bus.getExpenses());
		responseStructure.setData(map);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("found the bus by busId");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

	@Override
	public ResponseStructure<Bus> getByTimeArrivalDepature(String arrival, String depature, String date, String time) {
		List<Bus> bus = busDao.getByTimeArrivalDepature(arrival, depature, date, time);
		if(bus.isEmpty())
		{
			throw new TimeNotFoundException("In this time and in this date bus is not available check in other time or in other date");
		}
		List<Object> buslist = new ArrayList<>();
		if (bus.isEmpty()) {
		
		} else {
			for (Bus bus2 : bus) {
				LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
				map.put("busId          ", bus2.getBusId());
				map.put("busNo          ", bus2.getBusNo());
				map.put("arrival        ", bus2.getArrival());
				map.put("depature       ", bus2.getDepature());
				map.put("date           ", bus2.getDate());
				map.put("time           ", bus2.getTime());
				map.put("status         ", bus2.getStatus());
				map.put("seats available", bus2.getSeatsAvailable());
				map.put("ticket price   ", bus2.getTicketPrice());
				if (bus2.getRoutes() != null) {
					map.put("route          ", bus2.getRoutes());
				}
				if (!bus2.getListOfReview().isEmpty()) {
					map.put("review         ", bus2.getListOfReview());
				}
				buslist.add(map);
			}
		}
		responseStructure.setData(buslist);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("bus is available");
		responseStructure.setStatuscode(201);
		return responseStructure;
		// this is used to find all all bus that are coming at that data and time
	}

	
}
