package com.techlab.guitar.test1;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.techlab.guitar.model.EBuilder;
import com.techlab.guitar.model.EType;
import com.techlab.guitar.model.EWood;
import com.techlab.guitar.model.Guitar;
import com.techlab.guitar.model.GuitarSpec;
import com.techlab.guitar.model.Inventory;
import com.techlab.guitar.test.GuitarListPrint;
import com.techlab.guitar.test.GuitarSpecFactory;

public class GuitarInventoryTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Inventory inventory = new Inventory();
		int check = 0;
		while (true) {
			System.out.println();
			System.out.println("-------------Inventory-------------");
			System.out.println("1.Add Guitar\n2.Delete Guitar\n3.view Guitar\n4.Search Operartion\n5.exit");
			System.out.println("Enter you choice:- ");
			int choice = 0;
			while (true) {
				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
					break;
				} else {
					System.out.println("Enter correct numeric value!!");
					scanner.next();
				}
			}
			scanner.nextLine();

			switch (choice) {
			case 1:
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
				break;

			case 2:
				System.out.println("\n----------------------------------------");
				List<Guitar> guitarsDelete = inventory.getAll();
				if (guitarsDelete.size() != 0) {
					System.out.println("Enter the serial nuumber of guitar : ");
					String guitar = scanner.nextLine();
					inventory.deleteGuitar(guitar);
				}else {
					System.out.println("There is no guitar in inventory!!");
				}
				break;

			case 3:
				System.out.println("----------------All guitars in inventory--------------");
				List<Guitar> guitars = inventory.getAll();
				if (guitars.size() != 0) {
					GuitarListPrint.guitarListPrint(guitars);
				} else {
					System.out.println("No guitars are there!!");
				}
				break;

			case 4:
				System.out.println("\n----------------------------------------");
				List<Guitar> guitarSearch = inventory.getAll();
				if (guitarSearch.size() != 0) {
					System.out.println("Enter details for search :- ");
					GuitarSpec searchGuitarSpec = GuitarSpecInput.giveInput();
					List<Guitar> searchedGuitars = inventory.search(searchGuitarSpec);
					if (searchedGuitars.size() != 0)
						System.out.println("Founded guitars");
					GuitarListPrint.guitarListPrint(searchedGuitars);
					if (searchedGuitars.size() == 0) {
						System.out.println("No guiter found with this spcifiction!");
					}
				} else {
					System.out.println("No guitars are there!!");
				}
				break;

			case 5:
				System.out.println("Bye byee");
				System.exit(0);

			}

		}
	}
}
