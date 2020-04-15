package com.techelevator.npgeek.model.weather;

import java.util.List;

public interface ForecastDao {
	
	public List<Forecast> getForecastByParkCode(String parkCode);
	

}
