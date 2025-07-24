package com.techlab.guitar.test;

import java.util.List;

import com.techlab.guitar.model.Guitar;

public class GuitarListPrint {
	public static void guitarListPrint(List<Guitar> guitars) {
		for (int i = 0; i < guitars.size(); i++) {
			Guitar guitar = guitars.get(i);
			System.out.println("------------------------------------------------------");
			System.out.println("Index : "+(i+1));
			System.out.println("Serial number : " + guitar.getSerialNumber());
			System.out.println("Price : " + guitar.getPrice());
			System.out.println("Model : " + guitar.getSpec().getModel());
			System.out.println("Builder : " + guitar.getSpec().getBuilder());
			System.out.println("Type : " + guitar.getSpec().getType());
			System.out.println("Top Wood : " + guitar.getSpec().getTopWood());
			System.out.println("Back Wood : " + guitar.getSpec().getBackWood());
			System.out.println("------------------------------------------------------");

		}
	}

}
