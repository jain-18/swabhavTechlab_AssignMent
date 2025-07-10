package com.techlab.EcommerceDomain.model;

public interface PaymentGateway {
	int pay(double amount);
	int refund(double amount);
}
