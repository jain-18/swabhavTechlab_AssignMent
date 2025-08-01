package com.techlab.bank.model;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuWithoutLogin {
	public void outerMenu() throws ClassNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("--------Welcome to bank---------");
		while (true) {
			System.out.println("\n--------Menu---------");
			System.out.println("1. Add account.");
			System.out.println("2. View Accounts.");
			System.out.println("3. Login.");
			System.out.println("4. Exit.");
			int choice = 0;
			while (true) {
				System.out.print("Enter your choice : ");
				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
					break;
				} else {
					System.out.println("Enter integer only!!");
					scanner.next();
				}
			}

			switch (choice) {
			case 1:
				AccountManager.addAccount();
				break;

			case 2:
				AccountManager.viewAccounts();
				break;

			case 3:
				int accountNo = AccountManager.login();
				if(accountNo!=0) {
					LoginMenu lMenu = new LoginMenu(scanner);
					lMenu.start(accountNo);
				}
				break;

			case 4:
				System.exit(0);

			default:
				System.out.println("Enter valid option!!\n\n");
			}
		}
	}
}
