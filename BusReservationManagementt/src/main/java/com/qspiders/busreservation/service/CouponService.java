package com.qspiders.busreservation.service;

import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface CouponService {
	public ResponseStructure<?> addCoupon(Coupon coupon);

	public ResponseStructure<?> updateCoupon(int couponId);

	public ResponseStructure<?> removeCoupon(int couponId);
	
	public ResponseStructure<?>getCoupon(int couponId);
}
