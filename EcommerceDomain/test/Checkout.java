package com.techlab.EcommerceDomain.test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Checkout {
	public static void main(String[] args) {
		int check = 0;
		int choice = -1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Your Total Bill is :- ₹1000");
		System.out.println();
		System.out.println("How you want to pay bill?");
		System.out.println("1. Credit card");
		System.out.println("2. UPI");
		System.out.println("3. Net Banking");
		
		while (check == 0) {
			try {
				System.out.print("Enter your choice:");
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					CreditCardMethod.CreditCardPayment();
					check = 1;
					break;
				case 2:
					UPIMethod.upiPayment();
					check = 1;
					break;
				case 3:
					NetBankingMethod.NetBankingPayment();
					check = 1;
					break;
				case 4:
					System.exit(0);
				default:
					System.out.println("Invalid Input!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter integer only!!");
				scanner.next();
			}
		}

	}
}
