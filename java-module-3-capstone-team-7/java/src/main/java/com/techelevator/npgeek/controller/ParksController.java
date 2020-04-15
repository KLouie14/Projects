package com.techelevator.npgeek.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.user.User;
import com.techelevator.npgeek.model.user.UserDao;
import com.techelevator.npgeek.authentication.UnauthorizedException;
import com.techelevator.npgeek.authentication.AuthProvider;
import com.techelevator.npgeek.model.park.ParkDao;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyDao;
import com.techelevator.npgeek.model.weather.Forecast;
import com.techelevator.npgeek.model.weather.ForecastDao;

@Controller
public class ParksController {

	@Autowired              // automatically creates a permanent instantiation of the datasource, connects DAO to datasource
	ParkDao parkDao;

	@Autowired
	ForecastDao forecastDao;

	@Autowired
	SurveyDao surveyDao;

	@Autowired
	private AuthProvider auth;

	@Autowired
	UserDao userDao;

	@RequestMapping(path = "/home", method = RequestMethod.GET)		// get request will get data from server and defines the mapping for the /home path. 
	public String showHomePage(HttpSession mapHolder) throws UnauthorizedException { // method that passes in a session map called mapHolder that will be used for the entire session request
		if (auth.isLoggedIn()) { 			// auth object from AuthProvider and uses method isLoggedIn...if true.., if true, user can access home page
			mapHolder.setAttribute("parks", parkDao.getListOfParks());  // parkDao object from ParkDao and uses method, get listofparks and adds the key parks and and listofparks from method to the sessionmap
			return "home";		// brings user to home page
		} else {
			throw new UnauthorizedException();  // unauthorized exception if isLoggedIn = false, in other words, user not logged in
		}
	}

	@RequestMapping(path = "/detailPage", method = RequestMethod.GET) // get request will get data from server and defines the mapping for the /detailPage path.
	public String showDetailPage(@RequestParam String parkCode, HttpSession map) throws UnauthorizedException { // method takes in parameter parkCode taken from URL which is taken from the detail page when using c:url and c:param
		if (auth.isLoggedIn()) {                                // if isloggedin= true, will have access to detailpage
			map.setAttribute("forecasts", forecastDao.getForecastByParkCode(parkCode)); // add key forecasts and value which is the forecastbyparkcode passed in from the jsp to the url to the parameter in method
			map.setAttribute("park", parkDao.getParkByCode(parkCode));
			return "detailPage";
		} else {
			throw new UnauthorizedException();
		}															// need the parkcode in order to get to the park chosen
	}															

	@RequestMapping(path = "/detailPage", method = RequestMethod.POST) // post method will post data to the server obtained from the user's input
	public String processDetailPage(@RequestParam String parkCode, @RequestParam String degreeType, HttpSession map,
			RedirectAttributes aParkCode) {     			// taken the parkcode and degreeType from the param, and redirects us to the same page
		aParkCode.addAttribute("parkCode", parkCode);		// adds the key parkcode and the param value parkCode taken from URL to aParkCode that will be redirected to the detailpage
		map.setAttribute("degreeType", degreeType);			// sets the key we called degreeType and the param value degreeType taken from URL and redirected to the detailpage
		return "redirect:/detailPage";						// redirected to detailpage
	}															// data sent to sessionmap used for the request session

	@RequestMapping(path = "/survey", method = RequestMethod.GET)                 
	public String showSurveyPage(ModelMap mapHolder) throws UnauthorizedException {
		if (auth.isLoggedIn()) {
			if (!mapHolder.containsAttribute("Survey")) {     // if modelmap does not have an attribute key survey, will add a new survey object to the modelmap
				mapHolder.put("Survey", new Survey());					
			}
			mapHolder.put("parks", parkDao.getListOfParks());  // puts key parks and value of a list object of parks in the modelmap
			return "survey";				// return to survey view
		} else {
			throw new UnauthorizedException();						
		}
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)						// post request will post the data taken from the user
	public String processSuveyPage(@Valid @ModelAttribute("Survey") Survey surveyAnswerValues, BindingResult result,
			RedirectAttributes flash		// @valid validates the data in in survey, an object holding the data from survey is created, defines flashmap
								

	) {
		if (result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Survey", result); // adds binding result object to flashmap
			flash.addFlashAttribute("Survey", surveyAnswerValues); // adds object that has user's data to flashmap and sends data to view
			return "redirect:/survey";			//goes back to get for this URL
		}
		surveyDao.saveSurvey(surveyAnswerValues);  // if there are no errors, survey is saved and is redirected to favoriteparks view

		return "redirect:/favoriteParks";
	}

	@RequestMapping(path = "/favoriteParks", method = RequestMethod.GET)      // get request retrieves the data and defines mapping for favoriteparks loads page
	public String showFavoriteParks(ModelMap map) throws UnauthorizedException {
		if (auth.isLoggedIn()) {    // if user is logged in
			map.put("parks", parkDao.getFavoriteParks());		
			return "favoriteParks";			// gets list of favorite parks and puts it in the modelmap under key parks
		} else {
			throw new UnauthorizedException();
		}
	}

	@RequestMapping(path = { "/", "/login" }, method = RequestMethod.GET)
	public String index(ModelMap modelHolder) {		// method to give modelmap object
		modelHolder.put("user", auth.getCurrentUser());  // get current user and put in modelmap with the key user
		modelHolder.put("lastLogin", userDao.getLastLogin()); // get lastlogin and put in modelmap with key lastlogin
		return "login";			// returns the login view
	}
	


	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes flash) {
		if (auth.signIn(username, password)) { // takes in data values entered by user with @requestparam
			return "redirect:/home";	// if signin is true, redirects to home view
		} else {
			flash.addFlashAttribute("message", "Login Invalid"); // adds string with key message to flashmap and sends data to view
			flash.addFlashAttribute("hint", userDao.getPasswordHintByUsername(username));
			return "redirect:/login";		//if signin is false, redirects back to login
		}
	}

	@RequestMapping(path = "/logoff", method = RequestMethod.POST)  // sends data to server
	public String logOff() {
		auth.logOff();         // logs off after submit
		return "redirect:/";
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)// get request retrieves the data and defines mapping for register
	public String register(ModelMap modelHolder) {
		if (!modelHolder.containsAttribute("user")) {  // if modelmap object does not have the attribute user
			modelHolder.put("user", new User());	// add a new user with key user and send data to view
		}
		return "register";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes flash) {
		if (result.hasErrors()) { // validates data in user, object is created that holds data from user input, flashmap is defined
			flash.addFlashAttribute("user", user); // adds object of user that has data and sends to view
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result); // adds bindingresult object to flashmap and sends to view
			flash.addFlashAttribute("message", "Please fix the following errors:"); // adds value string with key message to flashmap and sends to view
			return "redirect:/register";
		}
		System.out.println(user.getEmail());  // if no errors, execute method and display email, redirect to register jsp
		auth.register(user.getUsername(), user.getPassword(), user.getPasswordHint(), user.getEmail());
		return "redirect:/";
	}

}