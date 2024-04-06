package com.qspiders.busreservation.busrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qspiders.busreservation.dto.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
	Review findByReviewId(int reviewId);
}
