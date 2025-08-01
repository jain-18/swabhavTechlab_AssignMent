package com.techlab.bank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseOperation {

	static MyConnection conn;

	public static int creatingAccount(String name, double balance, int pin)
			throws ClassNotFoundException, SQLException {
		String query = "insert into account(name,balance,pin) values(?,?,?)";
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			conn.setAutoCommit(false);
			ps.setString(1, name);
			ps.setDouble(2, balance);
			ps.setInt(3, pin);

			ps.executeUpdate();
			conn.commit();
			return showAccountNumber(name, balance, pin);

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		return -1;
	}

	public static int showAccountNumber(String name, double balance, int pin)
			throws ClassNotFoundException, SQLException {
		String query = "select accountno from account where name = ? and balance = ? and pin = ?  ORDER BY accountno DESC LIMIT 1";
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, name);
			ps.setDouble(2, balance);
			ps.setInt(3, pin);

			ResultSet rs = ps.executeQuery();
			int accountNo = 0;
			if (rs.next()) {
				accountNo = rs.getInt(1);
				System.out.println("Account created! Your account number is: " + accountNo + "\n");
				return accountNo;
			} else {
				System.out.println("❌ Could not find account after creation.\n");
				return -1;
			}

		}

	}

	public static boolean checkAccount(int accountNo, int pin) {
		String query = "select accountno,pin from account where accountno = ?";
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, accountNo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (pin == rs.getInt("pin")) {
					return true;
				} else {
					System.out.println("Wrong pin!!!");
					return false;
				}
			} else {
				System.out.println("This account number not existed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void insertTransaction(int accountCreatedNumber, double balance) {
		String query = "INSERT INTO transaction (accountno, transaction_time, ttype, amount, fromaccount, toaccount, balance) "
				+ "VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)";

		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, accountCreatedNumber); // accountno
			ps.setString(2, "Account creation"); // ttype
			ps.setDouble(3, balance); // amount
			ps.setNull(4, java.sql.Types.INTEGER); // fromaccount (null for account creation)
			ps.setNull(5, java.sql.Types.INTEGER); // toaccount (null for account creation)
			ps.setDouble(6, balance); // balance after creation

			int rowsInserted = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error inserting transaction: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
