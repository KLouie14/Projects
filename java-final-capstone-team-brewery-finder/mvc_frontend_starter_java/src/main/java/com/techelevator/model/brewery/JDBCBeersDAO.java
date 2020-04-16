package com.techelevator.model.brewery;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCBeersDAO implements BeersDAO  {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCBeersDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	@Override
	public List<Beers> getBeersByBreweryId(int id) {
		List<Beers> theBeers = new ArrayList <Beers>();
		String sqlGetBeerByBreweryId = "SELECT * from beer WHERE brewery_id = ?";          
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBeerByBreweryId, id);	 
		while (results.next()) {
		Beers aBeer = mapRowToBeer(results);							 
		theBeers.add(aBeer);
		}
		return theBeers;
	}
	
	@Override
	public void createBeer(Beers aBeer) {
		String sqlCreateBeer = "INSERT INTO beer (beer_id, beer_name, description, abv, beer_style, brewery_id)" + 
				  			   "VALUES(?, ?, ?, ?, ?, ?)";
		aBeer.setBeerId(getNextId());		
		jdbcTemplate.update(sqlCreateBeer, aBeer.getBeerId(),
										   aBeer.getName(),
										   aBeer.getDescription(),
										   aBeer.getAbv(),
										   aBeer.getBeerStyle(),
										   aBeer.getBreweryId());
										   

	}
	
	@Override
	public Beers getBeerByBeerId(int id) {
		Beers aBeer = new Beers();
		String sqlGetBeerByBeerId = "SELECT * from beer WHERE beer_id = ?";       
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBeerByBeerId, id);	 
		while (results.next()) {
		aBeer = mapRowToBeer(results);							  
		}
		return aBeer;
	}
	
	@Override
	public void deleteBeerById(int id) {
		String sqlDeleteBeerByBeerId = "Delete from beer WHERE beer_id = ?";
		jdbcTemplate.update(sqlDeleteBeerByBeerId, id);
	}
	
	
	private Beers mapRowToBeer(SqlRowSet results) {
		Beers aBeer;
		aBeer = new Beers();
		
		aBeer.setBeerId(results.getInt("beer_id"));
		aBeer.setName(results.getString("beer_name"));
		aBeer.setDescription(results.getString("description"));
		aBeer.setAbv(results.getDouble("abv"));
		aBeer.setBeerStyle(results.getString("beer_style"));
		aBeer.setBreweryId(results.getInt("brewery_id"));
		
		return aBeer;
	}
	
	private int getNextId() {
		String nextId = "select nextval('seq_beer_id')";
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet(nextId);
		int id = 0;
		
		if(nextIdResult.next()) {
			id = nextIdResult.getInt(1);
			return id;
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new beer");
		} 
	}
	@Override
	public List<String[]> getBeersRankedByAverageRatingByBreweryId(int breweryId) {
		String sqlGetRankedRatings = "SELECT beer.beer_id, beer_name, avg(rating) as avg_rating " +
									 "FROM beer LEFT JOIN beer_reviews " +
									 "on beer.beer_id = beer_reviews.beer_id " +
									 "WHERE beer.brewery_id = ? " +
									 "GROUP BY beer.beer_id " +
									 "Order by avg_rating desc nulls last";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRankedRatings, breweryId);
		
		List<String[]> rankedList = new ArrayList<String[]>();
		while (results.next()) {
		String[] rank = new String[3];
		rank[0] = results.getString("beer_id");
		rank[1]= results.getString("beer_name");
		if (results.getString("avg_rating") == null) {
			rank[2] = "0";
		}else {
		rank[2] = results.getString("avg_rating");
		
		double theRating = Double.valueOf(rank[2]);
		DecimalFormat df = new DecimalFormat("#.##"); 
		String formatted = df.format(theRating);
		rank[2] = formatted;
		
		
		}rankedList.add(rank);

		
	}return rankedList;
	
	
	}
	
	
}
