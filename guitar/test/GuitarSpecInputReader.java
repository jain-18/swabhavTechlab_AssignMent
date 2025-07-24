package com.techlab.guitar.test;

import java.util.Scanner;

public class GuitarSpecInputReader {
	private Scanner scanner = new Scanner(System.in);

    public String readModel() {
        String model;
        while (true) {
            System.out.print("Enter model: ");
            model = scanner.nextLine().trim();
            if (!model.isEmpty()) {
                break;
            }
            System.out.println("Model cannot be empty.");
        }
        return model;
    }

    public <T extends Enum<T>> T readEnum(Class<T> enumType, String message) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine().toUpperCase().trim();
                return Enum.valueOf(enumType, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Enter correct spelling.");
            }
        }
    }
}
