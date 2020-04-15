package com.techelevator;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	
	
	private static final String PURCHASE_OPTION_FEED_MONEY 			= "Feed Money";
	private static final String PURCHASE_OPTION_SELECT_PRODUCT      = "Select Product";
	private static final String PURCHASE_OPTION_FINISH_TRANSACTION  = "Finish Transaction";
	private static final String[] PURCHASE_OPTIONS = {  PURCHASE_OPTION_FEED_MONEY,
													    PURCHASE_OPTION_SELECT_PRODUCT,
													    PURCHASE_OPTION_FINISH_TRANSACTION
													    };
	
	
	
	
	private static Menu vendingMenu;              // Menu object to be used by an instance of this class
	private static Inventory inventory = new Inventory();
	private static Map<String, List<Snacks>> inventoryMap = inventory.getVendingMachineInventory();
	private static Balance balance = new Balance();
	private double currentBalanceInPennies = 0;
	
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	*
	***************************************************************************************************************************/

	public void run() {

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      // static attribute used as method is not associated with specific object instance
		inventoryDisplay();
		
		
		//System.out.println(vendingMachineInventory);
	}
	
	public void inventoryDisplay() {
		Set<String> theKeys = inventoryMap.keySet();
		for (String product : theKeys) {
			//inventoryMap.get(product).get(0).getName();
			System.out.println(product + ", " + inventoryMap.get(product).get(0).getName() + " " + inventoryMap.get(product).get(0).getPrice());
		}
	
}
	public void purchaseItems() {	 // static attribute used as method is not associated with specific object instance
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case PURCHASE_OPTION_FEED_MONEY:
					feedMoney();
					//System.out.println("Please enter 1, 2, 5, or 10 dollar bills.");
					break;                    // Exit switch statement
			
				case PURCHASE_OPTION_SELECT_PRODUCT:
					purchaseOption();
					
					break;                    // Exit switch statement
			
				case PURCHASE_OPTION_FINISH_TRANSACTION:
					endMethodProcessingForTransaction();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}	
		}
		return;                               
	}
	public void purchaseOption() {
		double currentBalance = 0;
	//	DecimalFormat df = new DecimalFormat("00.00");
		System.out.println("Please enter snack ID");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if (inventory.getIDs().contains(input)) {
			inventoryMap.get(input).get(0).getName();
		
		}
		else {
			System.out.println("Invalid input");
		}
		if((balance.getMoney() > inventoryMap.get(input).get(0).getPrice().doubleValue())) {
			currentBalance = balance.getMoney() - inventoryMap.get(input).get(0).getPrice().doubleValue();
			System.out.println("Purchased "+ inventoryMap.get(input).get(0).getName() + ". Your current balance is " + currentBalance);
			balance.setMoney(currentBalance);
		}
		else {
			System.out.println("The current balance is not enough.");
		}
		
		
}
	public void feedMoney() {
		System.out.println("Please enter 1, 2, 5, or 10 dollar bills");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		Set<String> validDollarBills = new HashSet<String>() {{
			add("1");
			add("2");
			add("5");
			add("10");
		}};
			
		if (validDollarBills.contains(input)) {
			balance.add(Double.parseDouble(input));
		
			System.out.println("The current balance is: " + balance.getMoney()); 
		}
		else {
			System.out.println("Vending Machine does not accept " + input + " dollar bills. Please put in valid input.");
		}
		}
	
	
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		System.out.println("Thank you, have a nice day!");
	}
	
	public void endMethodProcessingForTransaction() {
		int totalQuarters = 0;
		int totalDimes = 0;
		int totalNickels = 0;
		currentBalanceInPennies = balance.getMoney() * 100; 
			
			while (currentBalanceInPennies > 25) {
				
				currentBalanceInPennies -= 25;
				totalQuarters ++;
			} 
			
			while (currentBalanceInPennies > 10) {
				
				currentBalanceInPennies -= 10;
				totalDimes ++;
			} 
			while (currentBalanceInPennies > 5) {
				
				currentBalanceInPennies -= 5;
				totalNickels ++;
			} 
			
			
			System.out.println("Quarters: " + totalQuarters + " Dimes: " + totalDimes + " Nickels: " + totalNickels + " Remaining balance: " + currentBalanceInPennies);
			
		}
			
//		double quarter25 = .25;
//		double dimes10 = .10;
//		double nickel5 = .05;
//		int totalQuarters = (int) (currentBalance / quarter25); 
//		currentBalance = currentBalance % quarter25;
//		int totalDimes = (int) (currentBalance / dimes10);
//		currentBalance = currentBalance % dimes10;
//		int totalNickels = (int) (currentBalance / nickel5);
//		currentBalance = currentBalance % nickel5;
//		
//		System.out.println("Quarters: " + totalQuarters + " Dimes: " + totalDimes + " Nickels: " + totalNickels + " RemainingBalance: " + currentBalance);
//		
	}
	

