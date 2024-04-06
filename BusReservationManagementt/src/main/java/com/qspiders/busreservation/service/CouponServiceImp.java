package com.qspiders.busreservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.CouponDao;
import com.qspiders.busreservation.dao.UserDao;
import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.User;
import com.qspiders.busreservation.exceptionHandler.CheckCouponCodeIdException;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

@Service
public class CouponServiceImp implements CouponService {
	@Autowired
	CouponDao couponDao;
	@Autowired
	UserDao userDao;

	@Override
	public ResponseStructure<Coupon> addCoupon(Coupon coupon) {
		Coupon coupon2 = couponDao.addCoupon(coupon);
		List<User> listOfAllUsers = userDao.findAllUsers();
		if (!listOfAllUsers.isEmpty()) {
			for (User user : listOfAllUsers) {
				List<Coupon> coupons = user.getCoupons();
				coupons.add(coupon2);
				user.setCoupons(coupons);
			}
			userDao.saveall(listOfAllUsers);
		}
		ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
		responseStructure.setData(coupon2);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("coupon added successfully");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

	@Override
	public ResponseStructure<Coupon> updateCoupon(int couponId) {
		List<User> listOfUsers = userDao.findAllUsers();
		boolean flag = false;
		for (User users : listOfUsers) {
			List<Coupon> listOfCoupons = users.getCoupons();
			for (Coupon coupons : listOfCoupons) {
				if (coupons.getCouponId() == couponId) {
					coupons.setDiscountPercentage(20);
					users.setCoupons(listOfCoupons);
					userDao.save(users);
					flag = true;
					break;
				}
			}
			if (flag) {
				ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
				Coupon coupon = couponDao.findCouponById(couponId);
				System.out.println(coupon);
				responseStructure.setData(coupon);
				responseStructure.setDateTime(LocalDateTime.now());
				responseStructure.setMessage("coupon updated successfullyyy");
				responseStructure.setStatuscode(201);
				return responseStructure;
			}
		}
		throw new CheckCouponCodeIdException("check the coupon code's Id");
	}

	@Override
	public ResponseStructure<?> removeCoupon(int couponId) {
		Coupon coupon = couponDao.findCouponById(couponId);
		if (coupon == null) {
			throw new CheckCouponCodeIdException("check the coupon code's Id");
		}
		List<User> listOfUsers = userDao.findAllUsers();
		for (User user : listOfUsers) {
			List<Coupon> listOfCoupons = user.getCoupons();
			listOfCoupons.remove(coupon);
		}
		List<User> list = userDao.saveall(listOfUsers);
		couponDao.removeCoupon(coupon);
		// couponDao.removeCoupon(coupon);
		ResponseStructure<?> responseStructure = new ResponseStructure<>();
		responseStructure.setData(coupon);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("deleted coupon code id is " + couponId);
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

	@Override
	public ResponseStructure<Coupon> getCoupon(int couponId) {
		Coupon coupon = couponDao.findCouponById(couponId);
		if(coupon==null)
		{
			throw new CheckCouponCodeIdException("check the coupon code's Id");
		}
		ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
		responseStructure.setData(coupon);
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("deleted coupon code id is " + couponId);
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

}
