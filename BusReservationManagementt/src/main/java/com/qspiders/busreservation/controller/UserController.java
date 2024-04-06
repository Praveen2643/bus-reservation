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

import com.qspiders.busreservation.dao.CustomerDao;
import com.qspiders.busreservation.dao.UserDao;
import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.Review;
import com.qspiders.busreservation.dto.User;
import com.qspiders.busreservation.responsestructure.ResponseStructure;
import com.qspiders.busreservation.service.BusServiceImp;
import com.qspiders.busreservation.service.CustomerServiceImp;
import com.qspiders.busreservation.service.ReviewServiceImp;
import com.qspiders.busreservation.service.UserServiceImp;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@RequestMapping("/userrr")
@RestController
public class UserController {

	@Autowired
	UserServiceImp imp;
	
	@Autowired
	CustomerServiceImp customerServiceImp;
	@Autowired
	ReviewServiceImp reviewServiceImp;

	@PostMapping("/signup")

	public ResponseEntity<?> saveUser(@RequestBody User user) {
		ResponseStructure<User> responseStructure = imp.saveUser(user);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestParam int userId,String userPassword) {
		ResponseStructure<User> responseStructure = imp.updatePassword(userId, userPassword);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get{userId}")
	public ResponseEntity<?> getById(@PathVariable int userId) {
		ResponseStructure<User> responseStructure = imp.getUserById(userId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

		@GetMapping("/bookingHistory{userId}")
	public ResponseEntity<?> gettById(@PathVariable int userId) {
		ResponseStructure<Bookings> responseStructure = customerServiceImp.bookingHistory(userId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PostMapping("/addReview")
	public ResponseEntity<?> addReview(@RequestBody Review review) {
		ResponseStructure<Review> responseStructure = reviewServiceImp.saveReview(review);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateReview")
	public ResponseEntity<?> updateReview(@RequestBody Review review) {
		ResponseStructure<Review> responseStructure = reviewServiceImp.updateReview(review);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

	@PutMapping("deleteReview{reviewId}")
	public ResponseEntity<?> deleteReview(@RequestParam int reviewId,String userPassword) {
		ResponseStructure<Review> responseStructure = reviewServiceImp.deleteReview(reviewId);
		return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}
}
