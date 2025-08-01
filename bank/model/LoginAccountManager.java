package com.techlab.bank.model;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginAccountManager {

	static Scanner scanner;

	public LoginAccountManager(Scanner scanner) {
		this.scanner = scanner;
	}

	public static void transferMoney(int accountNo) throws ClassNotFoundException, SQLException {
		System.out.println("\n------Transfer Section----------");
		double amount = 0;
		double balance = LoginAccountDatabaseOperation.getBalanceOf(accountNo);
		if (balance != 0) {
			while (true) {
				System.out.print("Enter amount to tranfer: ");
				if (scanner.hasNextDouble()) {
					amount = scanner.nextDouble();
					if (amount > 0) {
						break;
					} else {
						System.out.println("Amount must be greater than 0");
					}
				} else {
					System.out.println("Enter digits only!!");
					scanner.next();
				}
			}
			int check = 0;
			int tranferAccountnumber = 0;
			while (check == 0) {
				System.out.print("Enter account number to tranfer : ");
				if (scanner.hasNextInt()) {
					tranferAccountnumber = scanner.nextInt();
					if (accountNo == tranferAccountnumber) {
						System.out.println("You cant sent money to yourself!!\n");
					} else {
						break;
					}
				} else {
					System.out.println("Enter integer only!!");
					scanner.next();
				}
			}
			if (LoginAccountDatabaseOperation.checkAccountNumber(tranferAccountnumber)) {
//				LoginAccountDatabaseOperation.transferMoney(accountNo, tranferAccountnumber, amount);
				LoginAccountDatabaseOperation.recordTranferRecord(accountNo,tranferAccountnumber,amount);
			} else {
				System.out.println("Invalid Account number!!");
			}
		} else {
			System.out.println("Insufficient amount to transfer!!");
		}
	}
}
