package com.qspiders.busreservation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qspiders.busreservation.busrepository.ReviewRepo;
import com.qspiders.busreservation.dto.Review;

@Repository
public class ReviewDao {
	@Autowired
	ReviewRepo reviewRepo;

	public Review getByReviewId(int reviewId) {
		Optional<Review> optional = reviewRepo.findById(reviewId);
		return optional.get();

	}
}
