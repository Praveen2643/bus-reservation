package com.qspiders.busreservation.busrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qspiders.busreservation.dto.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

}
