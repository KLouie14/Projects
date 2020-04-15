package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Snacks {
	public Drinks(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public void dispenseSnack() {
		String sound = "Glug Glug, Yum!";
		System.out.println(sound);
	}
	
	
	
}
