package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Snacks {
	public Candy(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public void dispenseSnack() {
		String sound = "Munch Munch, Yum!";
		System.out.println(sound);
	}

}
