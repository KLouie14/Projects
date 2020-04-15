package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Snacks{
	

	public Chips(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public void dispenseSnack() {
		String sound = "Crunch Crunch, Yum!";
		System.out.println(sound);
	}
		
	
	
}
