package com.techelevator;

import java.math.BigDecimal;

public abstract class Snacks {

	private String name;
	private BigDecimal price;
	private int stock;
	
	public Snacks(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	


	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
	public void dispenseSnack() {
		
	}
	@Override 
	public String toString() {
		return name + ", " + price;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
