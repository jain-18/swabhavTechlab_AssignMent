package com.techlab.bank.test;

import java.sql.SQLException;

import com.techlab.bank.model.MenuWithoutLogin;

public class BankTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		MenuWithoutLogin menu = new MenuWithoutLogin();
		menu.outerMenu();
	}
}
