package com.qspiders.busreservation.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusExceptionHandler {
	@ExceptionHandler(value = IdNotFoundException.class)
	ResponseEntity<?> idNotFoundExcep(IdNotFoundException exception) {
		ApiError apiError = new ApiError();
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("id not found");
		apiError.setExceptionClass(exception.getClass());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = TimeNotFoundException.class)
	ResponseEntity<?> wrongTime(TimeNotFoundException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setMessage("In this time and in this date bus is not available check in other time or in other date");
		apiError.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = InsufficientPointsException.class)
	ResponseEntity<?> InsufficientPointExcep(InsufficientPointsException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("insufficient points available");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = CheckYourCouponCodeException.class)
	ResponseEntity<?> CheckYourCouponCodeExcep(CheckYourCouponCodeException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("check coupon's code and try again...");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = CouponCodeExpiredException.class)
	ResponseEntity<?> CouponCodeExpiredExcep(CouponCodeExpiredException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("ur coupon has been expired");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = CouponAldreayUsedException.class)
	ResponseEntity<?> CouponAldreayUsedExcep(CouponAldreayUsedException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("u haved used this coupon already...");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = RefferalCodeAlreadyUsedException.class)
	ResponseEntity<?> RefferalCodeAlreadyUsedExcep(RefferalCodeAlreadyUsedException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("this reffered code has been used already...");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = SeatBookedException.class)
	ResponseEntity<?> SeatBookedExcep(SeatBookedException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("this seat is already booked check other seats");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = BookingNoNotFoundException.class)
	ResponseEntity<?> BookingNoNotFoundException(BookingNoNotFoundException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("check your booking number");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(value = CheckCouponCodeIdException.class)
	ResponseEntity<?> CheckYourCouponCodeIdExcep(CheckCouponCodeIdException exception) {
		ApiError apiError = new ApiError();
		apiError.setExceptionClass(exception.getClass());
		apiError.setDateTime(LocalDateTime.now());
		apiError.setMessage("check the Coupon code's id");
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
