package com.techelevator.model.review;

import java.util.List;


public interface ReviewDAO {
	
	public List<Review> getReviewsByBeerId(int beerId);
	
	//public Review createReview(int reviewId, int beerId, String reviewerName, String beerReview, int rating);
	
	public void saveReview(Review review);
	
	public List<Review> getAllReviews();
	
	public int getNextReviewId();
	
	public void deleteReview(); // access only to admins
	
	public double getAverageRatingByBeerId(int beerId);
	
}
