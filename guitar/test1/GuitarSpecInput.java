package com.techlab.guitar.test1;

import java.util.Scanner;

import com.techlab.guitar.model.EBuilder;
import com.techlab.guitar.model.EType;
import com.techlab.guitar.model.EWood;
import com.techlab.guitar.model.GuitarSpec;

public class GuitarSpecInput {
	public static GuitarSpec giveInput() {
		Scanner scanner = new Scanner(System.in);
		String model = "";
		while (true) {
			System.out.print("Enter model : ");
			model = scanner.nextLine();
			if(model != null) {
				break;
			}
		}

		System.out.println("\nEnter builder of guiter:- ");
		System.out.println("FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY;");
		EBuilder build = null;
		while (true) {
			try {
				System.out.print("Enter your choice : ");
				String builder = scanner.nextLine().toUpperCase();
				build = EBuilder.valueOf(builder);
				break;
			} catch (Exception e) {
				System.out.println("Enter correct Spelling");

			}
		}

		System.out.println("\nEnter Type of guiter:- ");
		System.out.println("ACOUSTIC, ELECTRIC");
		EType type = null;
		while (true) {
			try {
				System.out.print("Enter your choice : ");
				String typeString = scanner.nextLine().toUpperCase();
				type = EType.valueOf(typeString);
				break;
			} catch (Exception e) {
				System.out.println("Enter correct Spelling");
			}
		}

		System.out.println("\nEnter Type of top-wood of guiter:- ");
		System.out.println(
				"INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO,\nCEDAR, ADIRONDACK, ALDER, SITKA");
		EWood topWood = null;
		while (true) {
			try {
				System.out.print("Enter your choice : ");
				String woodString = scanner.nextLine().toUpperCase();
				topWood = EWood.valueOf(woodString);
				break;
			} catch (Exception e) {
				System.out.println("Enter correct Spelling");
			}
		}

		System.out.println("\nEnter Type of back-wood of guiter:- ");
		System.out.println(
				"INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO,\nCEDAR, ADIRONDACK, ALDER, SITKA");
		EWood backWood = null;
		while (true) {
			try {
				System.out.print("Enter your choice : ");
				String woodString = scanner.nextLine().toUpperCase();
				backWood = EWood.valueOf(woodString);
				break;
			} catch (Exception e) {
				System.out.println("Enter correct Spelling");
			}
		}

		return new GuitarSpec(build, model, type, backWood, topWood);
	}
}
