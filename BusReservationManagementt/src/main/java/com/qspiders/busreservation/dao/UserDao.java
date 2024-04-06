package com.qspiders.busreservation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qspiders.busreservation.busrepository.UserRepo;
import com.qspiders.busreservation.dto.Coupon;
import com.qspiders.busreservation.dto.User;

import jakarta.transaction.Transactional;

@Repository
public class UserDao {
	@Autowired
	UserRepo userRepo;

	public User save(User user) {
		User user2 = userRepo.save(user);
		return user2;
	}

	public User findByUserId(int userId) {
		Optional<User> user = userRepo.findById(userId);
		return user.get();
	}

	public User getRefferalCode(String referralCode) {
		User user = userRepo.findByReferralCode(referralCode);
		return user;
	}

	public List<User> findAllUsers() {
		List<User> list = userRepo.findAll();
		return list;
	}

	public List<User> saveall(List<User> listOfUser) {
		return userRepo.saveAll(listOfUser);
	}

	public User getUserById(int userId) {
		Optional<User> optional = userRepo.findById(userId);
		return optional.get();

	}
}
