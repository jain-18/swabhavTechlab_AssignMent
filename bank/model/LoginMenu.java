package com.techlab.bank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginMenu {
	Scanner scanner;

	LoginMenu(Scanner scanner) {
		this.scanner = scanner;
	}

	public void start(int accountNo) throws ClassNotFoundException, SQLException {

		int check = 0;
		while (check == 0) {
			System.out.println("\n-------Options---------");
			System.out.println("1. Transfer");
			System.out.println("2. View Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. View Transaction");
			System.out.println("6. Logout");
			int choice = 0;
			while (true) {
				System.out.print("Enter your choice : ");
				if (scanner.hasNextInt()) {
					choice = scanner.nextInt();
					break;
				} else {
					System.out.println("Enter integer only!!");
				}
			}

			switch (choice) {
			case 1:
				LoginAccountManager accountManager = new LoginAccountManager(scanner);
				accountManager.transferMoney(accountNo);
				break;

			case 2:
				double balance = LoginAccountDatabaseOperation.viewBalance(accountNo);
				if (balance >= 0) {
					System.out.println("Your balance is " + balance);
				} else {
					System.out.println("There is error while fetching balance!");
				}
				break;

			case 3:
				depositMoney(accountNo);
				break;

			case 4:
				withdraw(accountNo);
				break;

			case 5:
				printTransaction(accountNo);
				break;

			case 6:
				check = 1;
				break;

			default:
				System.out.println("Enter correct choice!!");
				;
			}
		}

	}

	private void printTransaction(int accountNo) {
	    String query = "SELECT * FROM transaction WHERE accountno = ? ORDER BY transaction_time DESC";

	    try (Connection conn = MyConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, accountNo);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n--- Transaction History for Account No: " + accountNo + " ---");
	        System.out.printf("%-25s %-15s %-12s %-15s %-15s %-12s\n", 
	                  "Time", "Type", "Amount", "FromAccount", "ToAccount", "Balance");


	        boolean hasData = false;

	        while (rs.next()) {
	            hasData = true;
	            String time = rs.getTimestamp("transaction_time").toString();
	            String type = rs.getString("ttype");
	            double amount = rs.getDouble("amount");
	            String from = rs.getString("fromaccount");
	            String to = rs.getString("toaccount");
	            double balance = rs.getDouble("balance");

	            System.out.printf("%-25s %-15s %-12.2f %-15s %-15s %-12.2f\n",
	                    time, type, amount,
	                    from == null ? "-" : from,
	                    to == null ? "-" : to,
	                    balance);

	        }

	        if (!hasData) {
	            System.out.println("No transactions found for this account.");
	        }

	    } catch (Exception e) {
	        System.out.println("Error retrieving transaction history: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	private void withdraw(int accountNo) throws ClassNotFoundException, SQLException {
		double amount = 0;
		double balance = LoginAccountDatabaseOperation.getBalanceOf(accountNo);
		while (true) {
			if (balance == 0) {
				System.out.println("Zero balance");
				break;
			}
			System.out.print("\nEnter amount to Withdraw : ");
			if (scanner.hasNextDouble()) {
				amount = scanner.nextDouble();
				if (amount > 0) {

					if (amount <= balance) {
						if (amount % 10 == 0) {
							if (LoginAccountDatabaseOperation.withDraw(accountNo, amount) == 1) {
								System.out.println("₹" + amount + " withdrawn successfully!!");
							} else {
								System.out.println("There is some problem while withdrawing!");
							}
							break;
						} else {
							System.out.println("Withdrawn Amount should be multiple of 10!");
						}
					} else {
						System.out.println("Insufficient Balance!!");
					}
				} else {
					System.out.println("Amount must be greater than 0");
				}
			} else {
				System.out.println("Enter digits only!");
				scanner.next();
			}
		}

	}

	public void depositMoney(int accountNo) throws ClassNotFoundException, SQLException {
		double amount = 0;
		while (true) {
			System.out.print("\nEnter amount to deposit : ");
			if (scanner.hasNextDouble()) {
				amount = scanner.nextDouble();
				if (amount > 0) {
					if (amount % 1 == 0) {
						if (LoginAccountDatabaseOperation.deposit(accountNo, amount) == 1) {
							System.out.println("₹" + amount + " deposited successfully!!");
						} else {
							System.out.println("Something went wrong");
						}
						break;
					} else {
						System.out.println("Deposit Amount should not be in decimal!");
					}
				} else {
					System.out.println("Amount must be greater than 0");
				}
			} else {
				System.out.println("Enter digits only!");
				scanner.next();
			}

		}

	}

}
