package com.qspiders.busreservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qspiders.busreservation.dao.BusDao;
import com.qspiders.busreservation.dao.ReviewDao;
import com.qspiders.busreservation.dto.Bus;
import com.qspiders.busreservation.dto.Review;
import com.qspiders.busreservation.responsestructure.ResponseStructure;

@Service
public class ReviewServiceImp implements ReviewService {

	@Autowired
	BusDao busDao;
	@Autowired
	ReviewDao reviewDao;

	@Override
	public ResponseStructure<Review> saveReview(Review review) {
		Bus bus = busDao.getByBusId(review.getBusId());
		List<Review> listOfReviews = bus.getListOfReview();
		listOfReviews.add(review);
		bus.setListOfReview(listOfReviews);
		busDao.save(bus);
		ResponseStructure<Review> responseStructure = new ResponseStructure<>();
		for (Review reviews : listOfReviews) {
			if (reviews.getUserId() == review.getUserId() && reviews.getDate() == review.getDate()) {
				responseStructure.setData(reviews);
				responseStructure.setDateTime(LocalDateTime.now());
				responseStructure.setMessage("review saved successfully");
				responseStructure.setStatuscode(201);
				break;
			}
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<Review> updateReview(Review review) {
		Bus bus = busDao.getByBusId(review.getBusId());
		List<Review> listOfReviews = bus.getListOfReview();
//		listOfReviews.add(review);

		ResponseStructure<Review> responseStructure = new ResponseStructure<>();
		for (Review reviews : listOfReviews) {
			if (reviews.getReviewId() == review.getReviewId()) {
				reviews.setRatings(5);
				bus.setListOfReview(listOfReviews);
				busDao.save(bus);
				responseStructure.setData(reviews);
				responseStructure.setDateTime(LocalDateTime.now());
				responseStructure.setMessage("review saved successfully");
				responseStructure.setStatuscode(201);
				break;
			}
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<Review> deleteReview(int reviewId) {
		Review review = reviewDao.getByReviewId(reviewId);
		int busId = review.getBusId();
		Bus bus = busDao.getByBusId(busId);
		List<Review> listOfReviews = bus.getListOfReview();
		listOfReviews.remove(review);
		bus.setListOfReview(listOfReviews);
		busDao.save(bus);
		ResponseStructure<Review> responseStructure = new ResponseStructure<>();
		responseStructure.setData("the review is deleted");
		responseStructure.setDateTime(LocalDateTime.now());
		responseStructure.setMessage("deleted successfully");
		responseStructure.setStatuscode(201);
		return responseStructure;
	}

}
