package com.techelevator.brewery.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;
import com.techelevator.model.brewery.Beers;
import com.techelevator.model.brewery.BeersDAO;
import com.techelevator.model.brewery.Brewery;
import com.techelevator.model.brewery.BreweryDao;
import com.techelevator.model.review.Review;
import com.techelevator.model.review.ReviewDAO;

@Controller
public class BreweryController {

	@Autowired // automatically creates a permanent instantiation of the datasource, connects
				// DAO to datasource
	BreweryDao breweryDao;
	@Autowired
	BeersDAO beerDao;
	@Autowired
	ReviewDAO reviewDao;
	@Autowired
	UserDAO userDao;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayHomePage(ModelMap mapHolder) {
		List<Brewery> listOfBreweries = breweryDao.getAllBreweries(); // use BreweryDao to get all the breweries
		mapHolder.addAttribute("allBreweries", listOfBreweries); // // Put the list in a Map (ModelMap) so View (jsp)
																	// can access it , could also use a requestMap and
																	// use requestattribute
		
		
		return "breweryList";
	}

	@RequestMapping(path = "/breweryDetails", method = RequestMethod.GET)
	public String displayBreweryDetailPage(ModelMap mapHolder, @RequestParam String userName, @RequestParam int id) {
		if ((userDao.userHasRole("beerLover", userName)) || (userDao.userHasRole("brewer", userName)) 
				|| (userDao.userHasRole("admin", userName))) {
			Brewery aBrewery = breweryDao.getBreweryById(id);
			mapHolder.addAttribute("theBrewery", aBrewery);
			List<Beers> beerList = beerDao.getBeersByBreweryId(id);
			mapHolder.put("allBeers", beerList);
			return "breweryDetails";
		}

		else {
			return "login";
		}
	}

	@RequestMapping(path = "/beerDetails", method = RequestMethod.GET)
	public String displayBeerDetailPage(ModelMap mapHolder, @RequestParam String userName, @RequestParam int beerId) {
		if (!mapHolder.containsAttribute("Review")) {     			// if mapHolder does not contain attribute review
			mapHolder.put("Review", new Review());					// put new review
		}
		if ((userDao.userHasRole("beerLover", userName)) || (userDao.userHasRole("brewer", userName)) || 
				(userDao.userHasRole("admin", userName))) {
		List<Review> reviews = reviewDao.getReviewsByBeerId(beerId);
		mapHolder.addAttribute("listOfReviews", reviews);
		Beers aBeer = beerDao.getBeerByBeerId(beerId);
		mapHolder.addAttribute("beers", aBeer);
		mapHolder.addAttribute("beerRating", reviewDao.getAverageRatingByBeerId(beerId));
		return "beerDetails";
	}
		else {
			return "login";
		}
		
}
	
	@RequestMapping(path = "/beerDetails", method = RequestMethod.POST)						
	public String processReviewsInBeerDetails(Review reviewAnswerValues) {	// get data entered in the beerdetail page and store in reviewAnswerValues
		reviewDao.saveReview(reviewAnswerValues);  			// use data from reviewanswervalues to update into database
		return "redirect:/beerDetails" + "?beerId="+ reviewAnswerValues.getBeerId()+"&userName=" + reviewAnswerValues.getReviewerName();
	}
	
	@RequestMapping(path = "/updateBeerList", method = RequestMethod.GET)
	public String updateBeerListPage(ModelMap mapHolder, @RequestParam int breweryId) {
		Brewery aBrewery = breweryDao.getBreweryById(breweryId);
		mapHolder.addAttribute("brewery", aBrewery);
		
		List<String[]> rankedBeers = beerDao.getBeersRankedByAverageRatingByBreweryId(breweryId);
		mapHolder.addAttribute("rankedBeers", rankedBeers);
		
		return "updateBeerList";
	}
	
	@RequestMapping(path = "/updateBeerList", method = RequestMethod.POST)
	public String addBeerToList(Beers addBeerFormValues) {
		beerDao.createBeer(addBeerFormValues);
		return "redirect:/updateBeerList" + "?breweryId=" + addBeerFormValues.getBreweryId();
		
	}
	
	@RequestMapping(path = "/deleteBeer", method = RequestMethod.POST)
	public String deleteBeer(@RequestParam String[] beerList, @RequestParam int breweryId) {
		for( String aBeer : beerList) {
			if ((aBeer != null) && (!aBeer.equals(""))) {
				int beerId = Integer.parseInt(aBeer); 
				beerDao.deleteBeerById(beerId);
			}
				
		}
		
		return "redirect:/updateBeerList" + "?breweryId=" + breweryId;
	}
	
	@RequestMapping(path= "/updateBreweryInfo", method = RequestMethod.GET) 
	public String updateBreweryInfo(ModelMap mapHolder, @RequestParam int breweryId) {
		Brewery aBrewery = breweryDao.getBreweryById(breweryId);
		mapHolder.addAttribute("brewery", aBrewery);
		return "updateBreweryInfo";
	}
	
	@RequestMapping(path="/updateBreweryInfo", method = RequestMethod.POST)
	public String processUpdateBreweryInfo(Brewery breweryFormValues, @RequestParam String userName, @RequestParam int id) {
		breweryDao.updateBreweryById(breweryFormValues, id);
		return "redirect:/breweryDetails" + "?id=" + id + "&userName=" + userName;
	}
	
	@RequestMapping(path= "/addBrewery", method = RequestMethod.GET)
	public String addNewBrewery() {
		return "createNewBrewery";
	}
	
	@RequestMapping(path= "/addBrewery", method = RequestMethod.POST) 
	public String processNewBrewery(Brewery breweryFormValues, @RequestParam String userName) {
		breweryDao.createBrewery(breweryFormValues);
		return "redirect:/breweryDetails" + "?id=" + breweryFormValues.getId() + "&userName=" + userName;
	}
	
}
