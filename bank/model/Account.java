package com.techlab.bank.model;

public class Account {
	private int accountno;
	private String name;
	private int pin;
	private double balance;

	public Account(int accountno, String name, int pin, double balance) {
		this.accountno = accountno;
		this.name = name;
		this.pin = pin;
		this.balance = balance;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public int getAccountno() {
		return accountno;
	}

	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

}
