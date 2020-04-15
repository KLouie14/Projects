package com.techelevator;

public class Balance {

	
	private double money = 0;
	private double balance1;
	private int quarters1;
	private int dime1;
	private int nickels1;

	public void giveChange(double balance) {

		balance1 = (balance * 100);
		quarters1 = ((int) balance1 / 25);
		balance1 = balance1 - (quarters1 * 25);
		dime1 = ((int) balance1 / 10);
		balance1 = balance1 - (dime1 * 10);
		nickels1 = ((int) balance1 / 5);
	}
	
	
	
	
	
	
	 public void add(double num) {	
		money = money + num;
	}


	public double getMoney() {
		return money;
	}


	public void setMoney(double money) {
		this.money = money;
	}
	
	
	public void returnMoney() {
		
	}
	
	
	 
	 
//	
//	 public void feedMoney(int addMoney) {
//		 money = money + addMoney;
//	}


//	public Balance add(int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	 
	
}
