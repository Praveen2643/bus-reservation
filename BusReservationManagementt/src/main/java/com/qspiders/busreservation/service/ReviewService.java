package com.qspiders.busreservation.service;


import com.qspiders.busreservation.dto.Review;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

public interface ReviewService {
	public ResponseStructure<?> saveReview(Review review);
	public ResponseStructure<?> updateReview(Review review);
	public ResponseStructure<?>deleteReview(int reviewId);
}
