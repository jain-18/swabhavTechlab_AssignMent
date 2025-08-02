package com.techlab.bank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAccountDatabaseOperation {

	public static double viewBalance(int accountNo) throws ClassNotFoundException, SQLException {
		String query = "select balance from account where accountno = ?";
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();

			double balance = -1;
			if (rs.next()) {
				return rs.getDouble("balance");
			}
			return balance;
		}
	}

	public static int deposit(int accountNo, double amount) throws SQLException, ClassNotFoundException {
		String depositQuery = "update account set balance = balance + ? where accountno = ?;";
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(depositQuery)) {

			ps.setDouble(1, amount);
			ps.setInt(2, accountNo);

			int update = ps.executeUpdate();
			
			recordDepositTransaction(accountNo, amount);
			return update;
			
		}
	}

	public static double getBalanceOf(int id) {
		String query = "SELECT balance FROM account WHERE accountno = ?;";
		double balance = 0;

		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					balance = resultSet.getDouble("balance");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ✅ Log exception
		}

		return balance;
	}

	public static int withDraw(int accountNo, double amount) throws ClassNotFoundException, SQLException {
		String depositQuery = "update account set balance = balance - ? where accountno = ?";
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(depositQuery)) {

			ps.setDouble(1, amount);
			ps.setInt(2, accountNo);

			int update = ps.executeUpdate();
			recordWithdrawTransaction(accountNo, amount);
			return update;
		}
	}

	public static boolean checkAccountNumber(int accountNo) throws ClassNotFoundException, SQLException {
		String depositQuery = "select accountno from account where accountno = ?";
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(depositQuery)) {
			ps.setInt(1, accountNo);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static void transferMoney(int senderId, int receiverId, double amount)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		// ✅ Fetch before transaction to avoid lock conflict
		String senderName = getNameOf(senderId);
		String receiverName = getNameOf(receiverId);

		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false); // Start transaction

			// Deduct money from sender
			String query = "UPDATE account SET balance = balance - ? WHERE accountno = ?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setDouble(1, amount);
				ps.setInt(2, senderId);
				ps.executeUpdate();
			}

			// Add money to receiver
			query = "UPDATE account SET balance = balance + ? WHERE accountno = ?";
			try (PreparedStatement ps1 = conn.prepareStatement(query)) {
				ps1.setDouble(1, amount);
				ps1.setInt(2, receiverId);
				ps1.executeUpdate();
			}

			conn.commit(); // Commit transaction
			System.out.println("Amount ₹" + amount + " successfully transferred to " + receiverName + " from "
					+ senderName + "!!!");

		} catch (Exception e) {
			if (conn != null) {
				conn.rollback(); // Roll back on any error
				System.err.println("Transaction failed. Rolled back.");
			}
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.setAutoCommit(true); // Reset auto-commit
				conn.close(); // Clean up connection
			}
		}
	}

	public static String getNameOf(int id) {
		String query = "SELECT name FROM account WHERE accountno = ?";
		String name = "";

		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					name = resultSet.getString("name");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // ✅ Log exception
		}

		return name;
	}

	public static void recordDepositTransaction(int account, double amount) {
		String query = "INSERT INTO transaction (accountno, transaction_time, ttype, amount, fromaccount, toaccount, balance) "
				+ "VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

		double updatedBalance = getBalanceOf(account);
		try (Connection conn = MyConnection.getConnection()) {

			// Insert transaction
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setInt(1, account); // accountno
				ps.setString(2, "Deposit"); // ttype
				ps.setDouble(3, amount); // amount
				ps.setNull(4, java.sql.Types.INTEGER); // fromaccount (null for deposit)
				ps.setNull(5, java.sql.Types.INTEGER); // toaccount (null for deposit)
				ps.setDouble(6, updatedBalance); // balance after deposit

				int rowsInserted = ps.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("Error recording deposit transaction: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void recordWithdrawTransaction(int account, double amount) {
	    String query = "INSERT INTO transaction (accountno, transaction_time, ttype, amount, fromaccount, toaccount, balance) "
	                 + "VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

	    double updatedBalance = getBalanceOf(account);  // Subtract amount for withdrawal

	    try (Connection conn = MyConnection.getConnection()) {

	        // Insert transaction
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, account);                     // accountno
	            ps.setString(2, "Withdraw");               // ttype
	            ps.setDouble(3, amount);                   // amount
	            ps.setNull(4, java.sql.Types.INTEGER);     // fromaccount (null for withdraw)
	            ps.setNull(5, java.sql.Types.INTEGER);     // toaccount (null for withdraw)
	            ps.setDouble(6, updatedBalance);           // balance after withdrawal

	            int rowsInserted = ps.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Withdraw transaction recorded successfully.");
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error recording withdraw transaction: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	public static void recordTranferRecord(int sender, int transferAccountNumber, double amount) {
	    String query = "INSERT INTO transaction (accountno, transaction_time, ttype, amount, fromaccount, toaccount, balance) "
	                 + "VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

	    String senderName = getNameOf(sender);
	    String receiverName = getNameOf(transferAccountNumber);
	    try (Connection conn = MyConnection.getConnection()) {

	        // Get balances
	        double senderBalance = getBalanceOf(sender);
	        double receiverBalance = getBalanceOf(transferAccountNumber);

	        // Validate balance
	        if (senderBalance < amount) {
	            System.out.println("Insufficient balance. Transfer aborted.");
	            return;
	        }

	        double updatedSenderBalance = senderBalance - amount;
	        double updatedReceiverBalance = receiverBalance + amount;

	        // Insert sender's transaction (Transfer)
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, sender);                      // accountno
	            ps.setString(2, "transfer");               // ttype
	            ps.setDouble(3, amount);                   // amount
	            ps.setInt(4, sender);                      // fromaccount
	            ps.setInt(5, transferAccountNumber);       // toaccount
	            ps.setDouble(6, updatedSenderBalance);     // balance after transfer

	            ps.executeUpdate();
	        }

	        // Insert receiver's transaction (Received)
	        try (PreparedStatement ps2 = conn.prepareStatement(query)) {
	            ps2.setInt(1, transferAccountNumber);        // accountno
	            ps2.setString(2, "Received");                // ttype
	            ps2.setDouble(3, amount);                    // amount
	            ps2.setInt(4, sender);                       // fromaccount
	            ps2.setInt(5, transferAccountNumber);        // toaccount
	            ps2.setDouble(6, updatedReceiverBalance);    // balance after receiving

	            ps2.executeUpdate();
	        }

	        // Update account balances
	        String updateQuery = "UPDATE account SET balance = ? WHERE accountno = ?";
	        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
	            // Update sender
	            updateStmt.setDouble(1, updatedSenderBalance);
	            updateStmt.setInt(2, sender);
	            updateStmt.executeUpdate();

	            // Update receiver
	            updateStmt.setDouble(1, updatedReceiverBalance);
	            updateStmt.setInt(2, transferAccountNumber);
	            updateStmt.executeUpdate();
	        }

	        System.out.println("₹"+amount+" money transfered from "+senderName+" to "+receiverName);

	    } catch (Exception e) {
	        System.out.println("Error recording transfer transaction: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	public static void changeName(int accountNo, String name) {
	    String query = "UPDATE account SET name = ? WHERE accountno = ?";

	    try (Connection conn = MyConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, name);
	        ps.setInt(2, accountNo);

	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Name updated successfully for account number " + accountNo + ".");
	        } else {
	            System.out.println("Account number " + accountNo + " not found.");
	        }

	    } catch (Exception e) {
	        System.out.println("Error while updating name: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	public static int getOldPin(int accountNo) {
	    String query = "SELECT pin FROM account WHERE accountno = ?";
	    
	    try (Connection conn = MyConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, accountNo);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("pin");
	        } else {
	            System.out.println("Account number not found.");
	        }

	    } catch (Exception e) {
	        System.out.println("Error retrieving PIN: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return -1;  // return -1 if account not found or error occurred
	}

	public static void changePin(int accountNo, int pin) {
	    String query = "UPDATE account SET pin = ? WHERE accountno = ?";

	    try (Connection conn = MyConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, pin);
	        ps.setInt(2, accountNo);

	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("PIN updated successfully for account number " + accountNo + ".");
	        } else {
	            System.out.println("Account number " + accountNo + " not found.");
	        }

	    } catch (Exception e) {
	        System.out.println("Error while updating PIN: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	public static Account getProfile(int accountNo) {
	    String query = "SELECT accountno, name, balance FROM account WHERE accountno = ?";
	    Account account = null;

	    try (Connection conn = MyConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setInt(1, accountNo);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            account = new Account();
	            account.setAccountno(rs.getInt("accountno"));
	            account.setName(rs.getString("name"));
	            account.setBalance(rs.getDouble("balance"));
	        }

	    } catch (Exception e) {
	        System.out.println("Error fetching account profile: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return account;
	}

	




}
