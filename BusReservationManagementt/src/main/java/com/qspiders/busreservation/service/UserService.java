package com.qspiders.busreservation.service;

import java.util.List;

import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.User;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface UserService {
	public ResponseStructure<?> saveUser(User user);

	public ResponseStructure<?> getUserById(int userId);

	public ResponseStructure<?> updatePassword(int userId,String userPassword);
}
