package com.techlab.guitar.test;

import java.util.List;
import java.util.Scanner;

import com.techlab.guitar.model.Guitar;
import com.techlab.guitar.model.GuitarSpec;
import com.techlab.guitar.model.Inventory;

public class InventoryMenu {
	private Inventory inventory;
	private Scanner scanner;

	public InventoryMenu(Inventory inventory, Scanner scanner) {
		this.inventory = inventory;
		this.scanner = scanner;
	}

	public void run() {
		while (true) {
			printMenu();
			int choice = readChoice();
			switch (choice) {
			case 1:
				addGuitar();
				break;
			case 2:
				deleteGuitar();
				break;
			case 3:
				viewGuitars();
				break;
			case 4:
				searchGuitars();
				break;
			case 5:
				exit();
				break;
			default:
				System.out.println("Enter between 1 to 5");
			}
		}
	}

	private void printMenu() {
		System.out.println("\n-------------Inventory-------------");
		System.out.println("1.Add Guitar\n2.Delete Guitar\n3.View Guitar\n4.Search\n5.Exit");
		System.out.print("Enter your choice: ");
	}

	private int readChoice() {
		while (!scanner.hasNextInt()) {
			System.out.print("Please enter a valid number: ");
			scanner.next();
		}
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}

	private void addGuitar() {
		System.out.println("\n----------------------------------------");
		String serialNumber = "";
		while (true) {
			System.out.print("Enter Serial Number : ");
			serialNumber = scanner.nextLine();
			if (inventory.containsSerialNumber(serialNumber) == 0) {
				break;
			}
		}

		System.out.print("Enter price of guitar : ");
		double price = 0.0;
		while (true) {
			if (scanner.hasNextDouble() || scanner.hasNextInt()) {
				price = scanner.nextInt();
				break;
			} else {
				System.out.println("Enter price correctly!!");
				System.out.print("Enter price of guitar : ");
				scanner.next();
			}
		}

		GuitarSpec guitarSpec = GuitarSpecFactory.createGuitarSpec();
		inventory.add(new Guitar(serialNumber, price, guitarSpec));

		System.out.println("Guitar added sucessfully!!!");
		System.out.println("\n----------------------------------------");
	}

	private void deleteGuitar() {
		System.out.println("\n----------------------------------------");
		List<Guitar> guitarsDelete = inventory.getAll();
		if (guitarsDelete.size() != 0) {
			System.out.println("Enter the serial nuumber of guitar : ");
			String guitar = scanner.nextLine();
			inventory.deleteGuitar(guitar);
		} else {
			System.out.println("There is no guitar in inventory!!");
		}
	}

	private void viewGuitars() {
		System.out.println("----------------All guitars in inventory--------------");
		List<Guitar> guitars = inventory.getAll();
		if (guitars.size() != 0) {
			GuitarListPrint.guitarListPrint(guitars);
		} else {
			System.out.println("No guitars are there!!");
		}
	}

	private void searchGuitars() {
		System.out.println("\n----------------------------------------");
		List<Guitar> guitarSearch = inventory.getAll();
		if (guitarSearch.size() != 0) {
			System.out.println("Enter details for search :- ");
			GuitarFlexibleSearchInput flexibleSearchInput = new GuitarFlexibleSearchInput();
			GuitarSpec searchGuitarSpec = flexibleSearchInput.searchInputs();
			if (searchGuitarSpec.getBuilder() == null && searchGuitarSpec.getModel() == null
					&& searchGuitarSpec.getType() == null && searchGuitarSpec.getBackWood() == null
					&& searchGuitarSpec.getTopWood() == null) {
				System.out.println("Please enter at least one field to search.");
			} else {
				List<Guitar> searchedGuitars = inventory.searchGuitar(searchGuitarSpec);
				if (searchedGuitars.size() != 0)
					System.out.println("Founded guitars");
				GuitarListPrint.guitarListPrint(searchedGuitars);
				if (searchedGuitars.size() == 0) {
					System.out.println("No guiter found with this spcifiction!");
				}
			}
		} else {
			System.out.println("No guitars are there!!");
		}

	}

	private void exit() {
		System.out.println("Bye bye");
		System.exit(0);
	}
}
