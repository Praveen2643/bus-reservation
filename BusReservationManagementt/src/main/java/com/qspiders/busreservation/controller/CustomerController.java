package com.qspiders.busreservation.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qspiders.busreservation.busrepository.BusRepo;
import com.qspiders.busreservation.dao.BusDao;
import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.responsestructure.ResponseStructure;
import com.qspiders.busreservation.service.BusServiceImp;
import com.qspiders.busreservation.service.CustomerServiceImp;

@RequestMapping("/customer")
@RestController
public class CustomerController {
	@Autowired
	BusServiceImp busServiceImp;

	@Autowired
	CustomerServiceImp customerServiceImp;

	@GetMapping("/checkavailability")
	public ResponseEntity<?> getByTimeArrivalDepature(@RequestParam String arrival, String depature, String date,
			String time) {
		ResponseStructure<Bus> responseStructure = busServiceImp.getByTimeArrivalDepature(arrival, depature, date,
				time);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PostMapping("/bookticket")
	public ResponseEntity<?> bookticket(@RequestBody Bookings bookings) {
		ResponseStructure<Bookings> responseStructure = customerServiceImp.bookTicket(bookings);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("/cancelTicket")
	public ResponseEntity<?> cancelTicket(@RequestParam int busId,int bookingNo) {
		ResponseStructure<Bookings> responseStructure = customerServiceImp.cancelTicket(busId, bookingNo);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}
	@PostMapping("/afterdeductionofpoints")
	public ResponseEntity<?> viewticketpricedeductingprice(@RequestBody Bookings bookings) {
		ResponseStructure<Bookings> responseStructure = customerServiceImp.viewticketpricedeductingprice(bookings);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}
}
