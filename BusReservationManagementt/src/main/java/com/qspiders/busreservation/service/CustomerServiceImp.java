package com.qspiders.busreservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.BusDao;
import com.qspiders.busreservation.dao.CustomerDao;
import com.qspiders.busreservation.dao.SeatDao;
import com.qspiders.busreservation.dao.UserDao;
import com.qspiders.busreservation.dto.Bookings;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.Expenses;
import com.qspiders.busreservation.dto.Routes;
import com.qspiders.busreservation.dto.Seats;
import com.qspiders.busreservation.dto.User;
import com.qspiders.busreservation.exceptionHandler.BookingNoNotFoundException;
import com.qspiders.busreservation.exceptionHandler.CheckYourCouponCodeException;
import com.qspiders.busreservation.exceptionHandler.CouponAldreayUsedException;
import com.qspiders.busreservation.exceptionHandler.CouponCodeExpiredException;
import com.qspiders.busreservation.exceptionHandler.IdNotFoundException;
import com.qspiders.busreservation.exceptionHandler.InsufficientPointsException;
import com.qspiders.busreservation.exceptionHandler.SeatBookedException;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	BusDao busDao;

	@Autowired
	SeatDao seatDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	UserDao dao;

	@Override
	public ResponseStructure<Bookings> bookTicket(Bookings bookings) {
		int userId = bookings.getUserId();
		User user = dao.getUserById(userId);
		System.out.println(bookings.getBusId());
		System.out.println(bookings.getSeatNo());
		Seats seats = seatDao.getSeat(bookings.getBusId(), bookings.getSeatNo());
		System.out.println(seats);
		System.out.println(seats.getStatus());
		if (seats.getStatus().equals("Booked")) {
			throw new SeatBookedException("this seat has been booked check other seats");
		}
		// only if points not equal to 0 given by user
		if (bookings.getPoints() != 0) {
			if (bookings.getPoints() > user.getPoints()) {
				throw new InsufficientPointsException("u does'nt have efficient points");
			} else {
//				points = user.getPoints() * 3;
//				user.setPoints(0);
				user.setPoints(user.getPoints() - bookings.getPoints());
				bookings.setTicketPrice(bookings.getTicketPrice() - bookings.getPoints());
			}
		}
		System.out.println(bookings.getCouponCode());
		// retriving all coupon to check where the coupon code the user give is a valid
		// coupon
		List<Coupon> couponsList = user.getCoupons();
		if (bookings.getCouponCode() != null) {// only if the user want to use coupon
			boolean flag = false;
			for (Coupon coupon : couponsList) {
				if (coupon.getCouponCode().equals(bookings.getCouponCode())) {
					// checks the user has entered the valid the coupon guven by the user

					String couponDateString = coupon.getExpires();
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate coupondate = LocalDate.parse(couponDateString, dateTimeFormatter);
					LocalDate today = LocalDate.now();

					if (coupondate.isBefore(today)) {
						couponsList.remove(coupon);
						user.setPoints(user.getPoints());
						user.setCoupons(couponsList);
						dao.save(user);
						throw new CouponCodeExpiredException("coupon code has been expired");
					}
					// coupon code that he has
					// if (coupon.getStatus().equals("Active")) {// check the status of the coupon
					// code
					else {
						flag = true;
						bookings.setTicketPrice((bookings.getTicketPrice()
								- bookings.getTicketPrice() * coupon.getDiscountPercentage() / 100)
								- bookings.getPoints());// setting
						// coupon.setStatus("Expired");
//						List<Coupon> listOfCoupon = user.getCoupons();
//						listOfCoupon.add(coupon);
						couponsList.remove(coupon);
						user.setCoupons(couponsList);
						dao.save(user);
						break;
					}
				}
			}
			if (!flag) {
				throw new CheckYourCouponCodeException("check your coupon code");
			}
		}
		Bus bus = busDao.getByBusId(bookings.getBusId());
		ArrayList<Bookings> bookingTickets = new ArrayList<>();
//		bookingTickets.add(bookings);
//		bus.setListOfBookings(bookingTickets);
		List<Bookings> bookings2 = bus.getListOfBookings();
		bookings2.add(bookings);
		bus.setListOfBookings(bookings2);
		bus.setSeatsAvailable(bus.getSeatsAvailable() - 1);
		if (bus.getSeatsAvailable() == 0) {
			bus.setStatus("Full");
		}
		Expenses expenses = bus.getExpenses();
		expenses.setTotalIncome(expenses.getTotalIncome() + bookings.getTicketPrice());
		bus.setExpenses(expenses);
		Bus bus1 = busDao.save(bus);
		int bookingNo = bookings.getBookingNo();
		Seats seat = seatDao.getSeat(bookings.getBusId(), bookings.getSeatNo());
		seat.setUserId(bookings.getUserId());
		seat.setBookingNo(bookingNo);
		seat.setPassengerName(bookings.getPassangerName());
		seat.setStatus("Booked");
		seatDao.save(seat);
//		ArrayList<Seats> seatList = new ArrayList<>();
//		seatList.add(seat);
//		bus1.setListOfSeats(seatList);
//		List<Seats>seatLists=bus1.getListOfSeats();
//		seatLists.add(seat);
//		bus1.setListOfSeats(seatLists);

		// busDao.save(bus1);
		Bookings bookings3 = customerDao.getBookings(bookingNo);
		ResponseStructure<Bookings> responseStructure2 = new ResponseStructure<>();
		responseStructure2.setData(bookings3);
		responseStructure2.setDateTime(LocalDateTime.now());
		responseStructure2.setMessage("booked successfully");
		responseStructure2.setStatuscode(201);
		return responseStructure2;
	}

	@Override
	public ResponseStructure<Bookings> cancelTicket(int busId, int bookingNo) {
		Bus bus = busDao.getByBusId(busId);
		Bookings bookings = customerDao.getBookings(bookingNo);
//		System.out.println("hi");
		List<Bookings> bookingsList = bus.getListOfBookings();
		String bookedDateStr = "";
		int ticketPrice = 0;
		boolean flag = false;
		for (Bookings bookingss : bookingsList) {
			if (bookingss.getBookingNo() == bookingNo) {
				System.out.println(bookingss);
				bookingsList.remove(bookingss);
				bookedDateStr = bookingss.getDate();
				ticketPrice = bookingss.getTicketPrice();
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new BookingNoNotFoundException("check the booking number");
		}
		Expenses expenses = bus.getExpenses();
		// calculation for cancellation amout
		LocalDate todayDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate bookedDate = LocalDate.parse(bookedDateStr + "", formatter);
		int daysDifference = (int) ChronoUnit.DAYS.between(bookedDate, todayDate);
		int cancellationCharges = 0;
		if (daysDifference == 1) {
			cancellationCharges = ticketPrice * 5 / 100;
			expenses.setTotalIncome(expenses.getTotalIncome() - ticketPrice + cancellationCharges);
		} else if (daysDifference == 2) {
			cancellationCharges = ticketPrice * 10 / 100;
			expenses.setTotalIncome(expenses.getTotalIncome() - ticketPrice + cancellationCharges);
		} else if (daysDifference == 3) {
			cancellationCharges = ticketPrice * 15 / 100;
			expenses.setTotalIncome(expenses.getTotalIncome() - ticketPrice + cancellationCharges);

		} else if (daysDifference > 3) {
			cancellationCharges = ticketPrice * 20 / 100;
			expenses.setTotalIncome(expenses.getTotalIncome() - ticketPrice + cancellationCharges);
		}

		bus.setListOfBookings(bookingsList);
		bus.setSeatsAvailable(bus.getSeatsAvailable() + 1);
		bus.setExpenses(expenses);
		bus.setStatus("Not Full");
		Bus bus2 = busDao.save(bus);
		Seats seats = seatDao.getSeat(bookings.getBusId(), bookings.getSeatNo());
		seats.setBookingNo(0);
		seats.setPassengerName(null);
		seats.setUserId(0);
		seats.setStatus("not booked");
		seatDao.save(seats);
		ResponseStructure<Bookings> responseStructure = new ResponseStructure<>();
		LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
		map.put("  ticketprice           ", ticketPrice);
		map.put("- cancellation charges  ", cancellationCharges);
		map.put("Balance(to be credited) ", ticketPrice - cancellationCharges);
		responseStructure.setData(map);
		responseStructure.setStatuscode(201);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("within 3 working days abount will be credited to your account");
		return responseStructure;

	}

	@Override
	public ResponseStructure<Bookings> viewticketpricedeductingprice(Bookings bookings) {
		User user = dao.findByUserId(bookings.getUserId());
		int points = user.getPoints();
		if (points >= bookings.getPoints()) {
			ResponseStructure<Bookings> responseStructure = new ResponseStructure<>();
			LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
			map.put("   ticketprice", bookings.getTicketPrice());
			map.put(" - points     ", points);
			map.put("   price      ", bookings.getTicketPrice() - points);
			responseStructure.setData(map);
			responseStructure.setStatuscode(201);
			responseStructure.setDateTime(LocalDateTime.now());
			responseStructure.setMessage("after deduction of points u have earned");
			return responseStructure;
		} else {
			throw new InsufficientPointsException("points are insufficient");
		}
	}

	@Override
	public ResponseStructure<Bookings> bookingHistory(int userId) {
		List<Bookings> listOfBookings = customerDao.getBookingHistory(userId);
		ResponseStructure<Bookings> responseStructure = new ResponseStructure<>();
		responseStructure.setData(listOfBookings);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("yours booking history");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}
}
