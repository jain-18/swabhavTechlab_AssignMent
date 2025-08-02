package com.techlab.bank.model;

import java.util.Scanner;

public class EditProfile {

	Scanner scanner;

	public EditProfile(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void viewProfile(int accountNo) {
	    Account account = LoginAccountDatabaseOperation.getProfile(accountNo);

	    if (account != null) {
	        System.out.println("\n==============================");
	        System.out.println("         ACCOUNT PROFILE       ");
	        System.out.println("==============================");
	        System.out.println("Account Number : " + account.getAccountno());
	        System.out.println("Name           : " + account.getName());
	        System.out.println("Balance        : ₹" + account.getBalance());
	        System.out.println("==============================\n");
	    } else {
	        System.out.println("No account found with account number: " + accountNo);
	    }
	}


	public void edit(int accountNo) {
		viewProfile(accountNo);
		System.out.println("\n-------------Edit profile----------");
		System.out.println("Select option to change : ");
		System.out.println("1. Change Account name");
		System.out.println("2. Change pin");
		int choice = 0;
		System.out.println("Enter your choice : ");
		while (true) {
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				break;
			} else {
				System.out.println("Enter integer only!!");
				System.out.print("Enter your choice : ");
				scanner.next();
			}
		}

		switch (choice) {
		case 1:
			changeName(accountNo);
			break;

		case 2:
			changePin(accountNo);
			break;

		default:
			System.out.println("Wrong choice! Enter correct choice!");
		}
	}

	private void changePin(int accountNo) {
		int pin = 0;
		int check = 0;
		while (check == 0) {
			System.out.print("Create your new 4 digit pin : ");
			if (scanner.hasNextInt()) {
				pin = scanner.nextInt();
				if (pin > 999 && pin < 10000) {
					int oldPin = LoginAccountDatabaseOperation.getOldPin(accountNo);
					if(oldPin == pin ) {
						System.out.println("Old pin and new pin are same!");
					}else if(oldPin == -1) {
						System.out.println("Something went wrong!! Please try again!!");
					}
					else {
						LoginAccountDatabaseOperation.changePin(accountNo,pin);
						check = 1;
					}
				} else {
					System.out.println("Create correct 4 digit pin!");
				}
			} else {
				System.out.println("Enter digits only!");
				scanner.next();
			}
		}
		
	}

	private void changeName(int accountNo) {
		scanner.nextLine();
		System.out.println("Enter first name only :-");
		String name = "";
		int check = 0;
		while (check == 0) {
			System.out.print("Enter name :- ");
			name = scanner.nextLine();
			if (name.matches("^[A-Za-z]+$") && name != null) {
				String oldName = LoginAccountDatabaseOperation.getNameOf(accountNo);
				if (name.equalsIgnoreCase(oldName)) {
					System.out.println("Old name and new name is same!");
				} else {
					LoginAccountDatabaseOperation.changeName(accountNo, name);
					check = 1;
				}
			} else {
				System.out.println("Enter name with alphabets only!!");

			}
		}
	}

}
