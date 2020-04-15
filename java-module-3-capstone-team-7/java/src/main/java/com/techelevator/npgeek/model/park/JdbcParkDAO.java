package com.techelevator.npgeek.model.park;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDAO implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getListOfParks() {
		List<Park> parkList = new ArrayList<Park>();
		String sqlGetListOfParks = "Select * from park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfParks);
		while (results.next()) {
			Park thePark = mapRowToPark(results);
			parkList.add(thePark);
		}
		return parkList;
	}

	@Override
	public Park getParkByCode(String parkCode) {
		Park aPark = new Park();
		String sqlGetParkByCode = "SELECT * from park where parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByCode, parkCode);
		while (results.next()) {
			aPark = mapRowToPark(results);												// set the park object to mapRowToPark get attributes
		}
		return aPark;
	}
	
	
	@Override
	public LinkedHashMap<Park, Integer> getFavoriteParks() {
		LinkedHashMap<Park, Integer> parksList = new LinkedHashMap<Park, Integer>();
		String sqlGetFavoriteParks = "Select parkcode, count(*) as numberofsurveys from survey_result group by parkcode order by numberofsurveys desc, parkcode asc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFavoriteParks);
		while(results.next()) {
			parksList.put((getParkByCode(results.getString("parkcode"))), results.getInt("numberofsurveys"));
		}
		return parksList;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park aPark;
		aPark = new Park();

		aPark.setParkCode(results.getString("parkcode"));
		aPark.setParkName(results.getString("parkname"));
		aPark.setState(results.getString("state"));
		aPark.setClimate(results.getString("climate"));
		aPark.setInspirationalQuote(results.getString("inspirationalquote"));
		aPark.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		aPark.setParkDescription(results.getString("parkdescription"));
		aPark.setAcreage(results.getInt("acreage"));
		aPark.setElevationInFeet(results.getInt("elevationinfeet"));
		aPark.setYearFounded(results.getInt("yearfounded"));
		aPark.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		aPark.setEntryFee(results.getInt("entryfee"));
		aPark.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		aPark.setNumberOfCampSites(results.getInt("numberofcampsites"));
		aPark.setMilesOfTrail(results.getDouble("milesoftrail"));

		return aPark;
	}

	
	
}
