package com.techelevator.model.brewery;

import java.util.List;

public interface BeersDAO {
		
		public List <Beers> getBeersByBreweryId(int id);
		
		public void createBeer(Beers aBeer);
		
		public Beers getBeerByBeerId(int id);

		public void deleteBeerById(int id);
		
		public List<String[]>  getBeersRankedByAverageRatingByBreweryId(int breweryId);
}
	
