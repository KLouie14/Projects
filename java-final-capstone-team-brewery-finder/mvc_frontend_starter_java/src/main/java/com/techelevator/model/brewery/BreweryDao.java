package com.techelevator.model.brewery;

import java.time.LocalTime;
import java.util.List;

public interface BreweryDao {
	
	public Brewery getBreweryById(int id);
	
	public void createBrewery(Brewery aBrewery);
	
	public void updateBreweryById(Brewery aBrewery, int id);
	
	public List<Brewery> getAllBreweries();

}
