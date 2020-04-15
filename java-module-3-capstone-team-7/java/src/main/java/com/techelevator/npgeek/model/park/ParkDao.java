package com.techelevator.npgeek.model.park;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface ParkDao {
	
	public List<Park> getListOfParks();
	
	public Park getParkByCode(String parkCode);
	
	public LinkedHashMap<Park, Integer> getFavoriteParks();
	
	
	
	
	
	
	
	

}
