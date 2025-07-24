package com.techlab.guitar.test;

import java.util.Scanner;

import com.techlab.guitar.model.Inventory;

public class GuitarInventoryTest1 {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Scanner scanner = new Scanner(System.in);
		InventoryMenu menu = new InventoryMenu(inventory, scanner);
		menu.run();
	}
}
