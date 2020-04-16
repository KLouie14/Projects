package com.techelevator.model.brewery;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCBreweryDAO implements BreweryDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCBreweryDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	@Override
	public Brewery getBreweryById(int id) {
		Brewery aBrewery = new Brewery();
		String sqlGetBreweryById = "SELECT * from brewery WHERE brewery_id = ?";          // sql statement to retrieve brewery by id
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBreweryById, id);	  // queries the data table and sets it to results
		while (results.next()) {
		aBrewery = mapRowToBrewery(results);							  // sets results to aBrewery
		}
		return aBrewery;
	}

	@Override
	public void createBrewery( Brewery aBrewery) {
		String sqlCreateBrewery = "INSERT INTO brewery (brewery_id, brewery_name, street_address, history, web_address, phone_number, hours_of_operation)" + 
				  "VALUES(?, ?, ?, ?, ?, ?, ?)";					// inserts attributes into database
		aBrewery.setId(getNextId());
		jdbcTemplate.update(sqlCreateBrewery, aBrewery.getId(), aBrewery.getName(), aBrewery.getAddress(), aBrewery.getHistory(), aBrewery.getWebAddress(), aBrewery.getPhoneNumber(), aBrewery.getTimeOfOperation());

	}
	
	@Override
	public void updateBreweryById(Brewery aBrewery, int id) {
		String sqlUpdateBrewery = "update brewery " + 
								  " set brewery_name = ?" +
								  " , street_address = ?" +
								  " , history = ?" +
								  " , web_address = ?" +
								  " , phone_number = ?" +
								  " , hours_of_operation = ?" +
								  
								  " where brewery_id = ?;";
		jdbcTemplate.update(sqlUpdateBrewery, aBrewery.getName(), aBrewery.getAddress(), aBrewery.getHistory(), aBrewery.getWebAddress(), aBrewery.getPhoneNumber(), aBrewery.getTimeOfOperation(), id);
				
	}

	@Override
	public List<Brewery> getAllBreweries() {
		List<Brewery> breweryList = new ArrayList<Brewery>();						// instantiate a list of breweries
		String sqlGetAllBreweries = "Select * FROM brewery ORDER BY brewery_name";						// set data from database to sqlGetAllBreweries
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllBreweries);		// creating sql rowset object and pass it to jdbc template.. results is equal to the query in db and will give the breweries
		while (results.next()) {													// while there is a next line
			Brewery aBrewery = mapRowToBrewery(results);							// 
			breweryList.add(aBrewery);												// add object brewery to list
		}
		return breweryList;
	}

	private Brewery mapRowToBrewery(SqlRowSet results) {
		Brewery aBrewery;
		aBrewery = new Brewery();
		
		aBrewery.setAddress(results.getString("street_address"));
		aBrewery.setHistory(results.getString("history"));
		aBrewery.setWebAddress(results.getString("web_address"));
		aBrewery.setId(results.getInt("brewery_id"));
		aBrewery.setName(results.getString("brewery_name"));
		aBrewery.setPhoneNumber(results.getString("phone_number"));
		aBrewery.setTimeOfOperation(results.getString("hours_of_operation"));
		
		return aBrewery;
	}
	
	
	private int getNextId() {
		String nextId = "select nextval('seq_brewery_id')";
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet(nextId);
		int id = 0;
		
		if(nextIdResult.next()) {
			id = nextIdResult.getInt(1);
			return id;
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new beer");
		} 
	}
	
}
