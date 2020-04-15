package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachine {

	public static void main(String[] args) {
		VendingMachineCLI vmc = new VendingMachineCLI(new Menu(System.in, System.out));
		vmc.run();
	}

}
