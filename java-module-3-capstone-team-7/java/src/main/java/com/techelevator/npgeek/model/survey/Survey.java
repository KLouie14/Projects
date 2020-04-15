package com.techelevator.npgeek.model.survey;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	private int surveyId;
	
	@NotBlank(message="Please select a park.")
	private String parkCode;
	
	@NotBlank(message="Please enter an email address.")
	@Email
	//@Pattern(regex="^")
	private String emailAddress;
	
	@NotBlank(message="Please select a state.")
	private String state;
	
	@NotBlank(message="Please choose an activity level.")
	private String activityLevel;
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	
	
}