package com.techlab.EcommerceDomain.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.techlab.EcommerceDomain.model.NetBanking;

public class NetBankingMethod {
	public static void NetBankingPayment() {
		System.out.println();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		int check = 0;
		double amount = 0;
		NetBanking netBanking = new NetBanking();
		while (check == 0) {
			try {
				while (check == 0) {
					System.out.print("Enter amount: ");
					 amount = scanner.nextDouble();
					check = netBanking.pay(amount);
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter number only!");
				scanner.next();
			}
		}
		scanner.nextLine();
		check = 0;
		while (check == 0) {
			try {
			System.out.println("Do you want it refund?(y/n)");
			String response = scanner.nextLine().toLowerCase();
			if(response.compareTo("y")==0) {
				netBanking.refund(amount);
				check = 1;
			}else if(response.compareTo("n")==0){
				check = 1;
			}
			else {
				System.out.println("Invalid Input");
			}
			}catch(InputMismatchException e) {
				System.out.println("Enter number only!");
			}
		}
	}

}
