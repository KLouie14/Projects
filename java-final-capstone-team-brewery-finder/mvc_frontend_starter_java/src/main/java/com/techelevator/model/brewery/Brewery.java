package com.techelevator.model.brewery;

import java.time.LocalTime;

public class Brewery {
	
	private int id;
	private String name;
	private String webAddress;
	private String phoneNumber;
	private String history;
	private String timeOfOperation;
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getTimeOfOperation() {
		return timeOfOperation;
	}
	public void setTimeOfOperation(String timeOfOperation) {
		this.timeOfOperation = timeOfOperation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
