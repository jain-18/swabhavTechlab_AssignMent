package com.techlab.bank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class AccountManager {
	static Scanner scanner = new Scanner(System.in);

	public static void addAccount() throws ClassNotFoundException, SQLException {
		int check = 0;
		System.out.println("\n----------Adding Account-----------");
		String name = "";
		while (check == 0) {
			System.out.print("Enter name :- ");
			name = scanner.nextLine();
			if (name.matches("^[A-Za-z]+$") && name != null) {
				check = 1;
			} else {
				System.out.println("Enter name with alphabets only!!");
				
			}
		}

		check = 0;
		double balance = 0;
		while (check == 0) {
			System.out.print("Enter balance : ");
			if (scanner.hasNextDouble()) {
				balance = scanner.nextDouble();
				if (balance > 0) {
					check = 1;
				} else {
					System.out.println("Enter balance greater than 0!");
				}
			} else {
				System.out.println("Enter correct balance!");
				scanner.next();
			}
		}
		check = 0;
		int pin = 0;
		while (check == 0) {
			System.out.print("Create your 4 digit pin : ");
			if (scanner.hasNextInt()) {
				pin = scanner.nextInt();
				if (pin > 999 && pin < 10000) {
					check = 1;
				} else {
					System.out.println("Create correct 4 digit pin!");
				}
			} else {
				System.out.println("Enter digits only!");
				scanner.next();
			}
		}

		int accountCreatedNumber = DataBaseOperation.creatingAccount(name, balance, pin);
		if(accountCreatedNumber != 0) {
			DataBaseOperation.insertTransaction(accountCreatedNumber,balance);
		}

	}

	public static void viewAccounts() throws ClassNotFoundException, SQLException {
		String query = "select * from account";
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();

			System.out.println("\n\n------ Account List ------");
			System.out.printf("%-10s %-20s %n", "AccNo", "Name");
			System.out.println("--------------------------");

			while (rs.next()) {
				int accountNo = rs.getInt("accountno");
				String name = rs.getString("name");
				double balance = rs.getDouble("balance");

				System.out.printf("%-10d %-20s\n", accountNo, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int login() {
		int check = 0;
		int accountNo = 0;
		System.out.println("\n---------Login--------");
		while (check == 0) {
			System.out.print("Enter account number : ");
			if (scanner.hasNextInt()) {
				accountNo = scanner.nextInt();
				if (accountNo > 0) {
					check = 1;
				}else {
					System.out.println("Enter valid account number!");
				}
			}else {
				System.out.println("Enter digits only!!");
			}
		}

		int pin = 0;
		check = 0;
		while (check == 0) {
			System.out.print("Enter your 4 digit pin : ");
			if (scanner.hasNextInt()) {
				pin = scanner.nextInt();
				if (pin > 999 && pin < 10000) {
					check = 1;
				} else {
					System.out.println("Create correct 4 digit pin!");
				}
			} else {
				System.out.println("Enter digits only!");
			}
		}
		
		if(DataBaseOperation.checkAccount(accountNo,pin)) {
			return accountNo;
		}else {
			return 0;
		}

	}

}
