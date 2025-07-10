package com.techlab.EcommerceDomain.model;

public class UPI implements PaymentGateway{
	
	public double pay = 1000;
	public double payed = 1000;
	@Override
	public int pay(double amount) {
			if(amount == pay) {
				System.out.println("Payment is successfull");
				pay = 0;
				return 1;
			}else if(amount>0 && amount>pay){
				System.out.println("Pay balance amount");
			}else {
				System.out.println("Enter Valid amount");
			}
		return 0;
	}

	@Override
	public int refund(double amount) {
		System.out.println("Amount to be refuned: "+amount);
		System.out.println("The amount is refuned successfully!!");
		return 0;
	}

}
