package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Snacks {
	
	public Gum(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public void dispenseSnack() {
		String sound = "Chew Chew, Yum!";
		System.out.println(sound);
	}
	
	
}
