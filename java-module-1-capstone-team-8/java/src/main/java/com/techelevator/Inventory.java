package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Inventory  { 
	
	
	public Inventory() {
		fileImporter();
	}
	

	private Map<String, List<Snacks>> vendingMachineInventory = new TreeMap<>();
	
	public Set<String> getIDs(){
		return vendingMachineInventory.keySet();
	}
	
	public Map<String, List<Snacks>> getVendingMachineInventory(){
		return vendingMachineInventory;
	}
	
	private Map<String, List<Snacks>> fileImporter() {
		File inventoryFile = new File("vendingmachine.csv");

		try (Scanner stockFiller = new Scanner(inventoryFile)) {
			while (stockFiller.hasNextLine()) {
				String line = stockFiller.nextLine();
				if (!line.isEmpty()) {
					String[] stockFillerArray = line.split("\\|");

					int maxItems = 5;
					if (stockFillerArray[0].contains("A")) {						// if id has an A
						List<Snacks> snacksList = new ArrayList<>();				// creating a new arrayList called snacksList
						for (int i = 0; i < maxItems; i++) {						// creating 
							Chips temp = new Chips(stockFillerArray[1],				// creating new object of chips getting name and price from the file
									new BigDecimal(stockFillerArray[2]));
							snacksList.add(temp);									// putting the object inside the arrayList
						}
						vendingMachineInventory.put(stockFillerArray[0], snacksList);// putting the object inside a map with the id as key and object as the value

					} else if (stockFillerArray[0].contains("B")) {
						List<Snacks> snacksList = new ArrayList<>();
						for (int i = 0; i < maxItems; i++) {
							Candy temp = new Candy(stockFillerArray[1],
									new BigDecimal(stockFillerArray[2]));
							snacksList.add(temp);
						}
						vendingMachineInventory.put(stockFillerArray[0], snacksList);

					} else if (stockFillerArray[0].contains("C")) {
						List<Snacks> snacksList = new ArrayList<>();
						for (int i = 0; i < maxItems; i++) {

							Drinks temp = new Drinks(stockFillerArray[1],
									new BigDecimal(stockFillerArray[2]));
							snacksList.add(temp);
						}
						vendingMachineInventory.put(stockFillerArray[0], snacksList);

					} else {
						List<Snacks> snacksList = new ArrayList<>();

						for (int i = 0; i < maxItems; i++) {
							Gum temp = new Gum(stockFillerArray[1],
									new BigDecimal(stockFillerArray[2]));
							snacksList.add(temp);
						}
						vendingMachineInventory.put(stockFillerArray[0], snacksList);
					}
					
				}
				
			}
				
			return vendingMachineInventory;

		} catch (FileNotFoundException e) {
			System.out.println("Your file does not exist");
			System.exit(1);
			return vendingMachineInventory;
			
		}
		
	}
}
