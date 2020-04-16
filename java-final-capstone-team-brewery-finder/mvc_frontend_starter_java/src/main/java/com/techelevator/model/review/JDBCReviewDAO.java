package com.techelevator.model.review;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCReviewDAO implements ReviewDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCReviewDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Review> getReviewsByBeerId(int beerId) {
		
		List<Review> reviewList = new ArrayList<Review>();
		String sqlGetReviewsByBeerId = "SELECT * from beer_reviews where beer_id = ? order by rating desc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReviewsByBeerId, beerId);
		while (results.next()) {
			Review aReview = mapRowToReview(results);
			reviewList.add(aReview);
		}
		return reviewList;
	}

//	@Override
//	public Review createReview(int reviewId, int beerId, String reviewerName, String beerReview, int rating) {
//		String sqlCreateReview = "INSERT into beer_reviews (beer_reviews_id, beer_id, reviewer_name, beer_review, rating)" +
//									"VALUES(?, ?, ?, ?, ?)";
//		jdbcTemplate.update(sqlCreateReview, reviewId, beerId, reviewerName, beerReview, rating);
//		
//		Review aReview = new Review();
//		
//		aReview.setBeerId(beerId);
//		aReview.setBeerReview(beerReview);
//		aReview.setRating(rating);
//		aReview.setReviewerName(reviewerName);
//		aReview.setReviewId(reviewId);
//		
//		return aReview;
//	}
	
	@Override
	public void saveReview(Review review) {
		String sqlSaveReview = "INSERT into beer_reviews (beer_reviews_id, beer_id, reviewer_name, beer_review, rating)" +
									"VALUES(?, ?, ?, ?, ?)";
		review.setReviewId(getNextReviewId());
		jdbcTemplate.update(sqlSaveReview, review.getReviewId(), review.getBeerId(), review.getReviewerName(), review.getBeerReview(), review.getRating());
		
	}

	@Override
	public List<Review> getAllReviews() {
		List<Review> reviewList = new ArrayList<Review>();
		String sqlGetAllReviews = "SELECT * from beer_reviews";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllReviews);
		while (results.next()) {
			Review aReview = mapRowToReview(results);
			reviewList.add(aReview);
		}
		return reviewList;
	}

	@Override
	public void deleteReview() {				// access given only to admin
		// TODO Auto-generated method stub
		
	}
	
	
	
	private Review mapRowToReview(SqlRowSet results) {
		Review aReview;
		aReview = new Review();
		
		aReview.setReviewId(results.getInt("beer_reviews_id"));
		aReview.setBeerId(results.getInt("beer_id"));
		aReview.setRating(results.getInt("rating"));
		aReview.setReviewerName(results.getString("reviewer_name"));
		aReview.setBeerReview(results.getString("beer_review"));
		
		return aReview;
	}

	public int getNextReviewId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_beer_reviews_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new review.");
		}
	}

	@Override
	public double getAverageRatingByBeerId(int beerId) {
		List<Double> ratings = new ArrayList<>();
		String sqlGetAverageRating = "SELECT rating " +
									 "FROM beer_reviews " +
									 "WHERE beer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAverageRating, beerId);
		
		while (results.next()) {
				ratings.add(results.getDouble(1));
		}
		double averageRating = 0.0;
		double sum = 0.0;
		for(int i = 0; i< ratings.size(); i++ ) {
			sum += ratings.get(i);
		}
		if (sum == 0) {
			averageRating = 0;
		}
		else {
		averageRating = sum/ratings.size();

	} 	DecimalFormat df = new DecimalFormat("#.##"); 
		String formatted = df.format(averageRating);
		double theRating = Double.valueOf(formatted);

		
		return theRating;
	
}
}
