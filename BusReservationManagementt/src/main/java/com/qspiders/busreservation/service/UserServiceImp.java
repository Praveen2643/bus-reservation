package com.qspiders.busreservation.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.CouponDao;
import com.qspiders.busreservation.dao.UserDao;
import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.User;
import com.qspiders.busreservation.exceptionHandler.RefferalCodeAlreadyUsedException;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserDao dao;
	@Autowired
	CouponDao couponDao;

	@Override
	public ResponseStructure<User> saveUser(User user) {
		if (user.getReferredCode() == null) {
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			SecureRandom random = new SecureRandom();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 8; i++) {
				int randomIndex = random.nextInt(alphabet.length());
				char randomChar = alphabet.charAt(randomIndex);
				sb.append(randomChar);
			}
			user.setReferralCode(sb.toString());
			user.setPoints(0);
			List<Coupon> couponsList = couponDao.findAllCoupon();
			user.setCoupons(couponsList);
			User user2 = dao.save(user);
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			// User user2 = dao.findByUserId(user.getUserId());
			responseStructure.setData(user2);
			responseStructure.setDateTime(LocalDateTime.now());
			responseStructure.setMessage("signed up successfull");
			responseStructure.setStatuscode(201);
			return responseStructure;
		} else {
			// should add exception handler
			User userr = dao.getRefferalCode(user.getReferredCode());
			System.out.println(userr);
			System.out.println(user.getReferredCode());
			if (userr == null) {
				ResponseStructure<User> responseStructure = new ResponseStructure<>();
				responseStructure.setData("Incorrect reffereal code retry...");
				responseStructure.setDateTime(LocalDateTime.now());
				responseStructure.setMessage("please enter valid refferal code");
				responseStructure.setStatuscode(201);
				return responseStructure;
			} else {

				userr.setPoints(userr.getPoints() + 10);
				dao.save(userr);
				String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
				SecureRandom random = new SecureRandom();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 8; i++) {
					int randomIndex = random.nextInt(alphabet.length());
					char randomChar = alphabet.charAt(randomIndex);
					sb.append(randomChar);
				}
				user.setReferralCode(sb.toString());
				user.setPoints(5);
				List<Coupon> couponsList = couponDao.findAllCoupon();
				user.setCoupons(couponsList);
				User user2 = dao.save(user);
				ResponseStructure<User> responseStructure = new ResponseStructure<>();
				responseStructure.setData(user2);
				responseStructure.setDateTime(LocalDateTime.now());
				responseStructure.setMessage("signed up successfull");
				responseStructure.setStatuscode(201);
				return responseStructure;
			}
		}
	}

	@Override
	public ResponseStructure<User> getUserById(int userId) {
		User user = dao.getUserById(userId);
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setData(user);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("user details");
		responseStructure.setStatuscode(201);
		return responseStructure;

	}

	@Override
	public ResponseStructure<User> updatePassword(int userId, String userPassword) {
		User user = dao.getUserById(userId);
		user.setUserPassword(userPassword);
		User user2 = dao.save(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setData(user2);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("password updated successfully");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

}
