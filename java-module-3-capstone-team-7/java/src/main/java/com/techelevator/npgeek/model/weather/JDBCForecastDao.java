package com.techelevator.npgeek.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCForecastDao implements ForecastDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCForecastDao (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Forecast> getForecastByParkCode(String parkCode) {
		List<Forecast> forecasts = new ArrayList<Forecast>();
		String sqlGetForecastByParkcode = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetForecastByParkcode, parkCode);
		
		while(results.next()) {
			Forecast forecast = mapRowToForecast(results);
			forecasts.add(forecast);
		}
		return forecasts;
	}
	
	
	public Forecast mapRowToForecast(SqlRowSet results) {
		Forecast forecast = new Forecast();
		forecast.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		forecast.setLow(results.getInt("low"));
		forecast.setHigh(results.getInt("high"));
		forecast.setForecast(results.getString("forecast"));
		forecast.setParkCode(results.getString("parkcode"));
		return forecast;
	}
	

}
