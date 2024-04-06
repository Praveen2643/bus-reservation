package com.qspiders.busreservation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qspiders.busreservation.busrepository.CouponRepo;
import com.qspiders.busreservation.dto.Coupon;

@Repository
public class CouponDao {
	@Autowired
	CouponRepo couponRepo;

	public List<Coupon> findAllCoupon() {
		List<Coupon> coupons = couponRepo.findAll();
		return coupons;
	}

	public List<Coupon> saveAll(List<Coupon> couponslist) {
		List<Coupon> couponsList = couponRepo.saveAll(couponslist);
		return couponslist;
	}

	public Coupon addCoupon(Coupon coupon) {
		Coupon coupon2 = couponRepo.save(coupon);
		return coupon2;
	}

	public Coupon findCouponById(int couponId) {
		Optional<Coupon> optional = couponRepo.findById(couponId);
		return optional.get();
	}

	public void removeCoupon(Coupon coupon) {
		couponRepo.delete(coupon);
	}
}
