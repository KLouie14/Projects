package com.techelevator.model.review;

public class Review {
	
	private int reviewId;
	private int beerId;
	private String reviewerName;
	private String beerReview;
	private int rating;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getBeerId() {
		return beerId;
	}
	public void setBeerId(int beerId) {
		this.beerId = beerId;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getBeerReview() {
		return beerReview;
	}
	public void setBeerReview(String beerReview) {
		this.beerReview = beerReview;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
