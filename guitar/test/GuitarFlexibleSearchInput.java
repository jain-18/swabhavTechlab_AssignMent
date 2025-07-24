package com.techlab.guitar.test;

import java.util.Scanner;

import com.techlab.guitar.model.EBuilder;
import com.techlab.guitar.model.EType;
import com.techlab.guitar.model.EWood;
import com.techlab.guitar.model.GuitarSpec;

public class GuitarFlexibleSearchInput {
	private Scanner scanner = new Scanner(System.in);

    public GuitarSpec searchInputs() {
        System.out.print("Model: (Press Enter to skip) ");
        String model = scanner.nextLine().trim();
        if (model.isEmpty()) model = null;

        EBuilder builder = readEnumAllowSkip(EBuilder.class,
                "Builder (FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY) (Press Enter to skip)");

        EType type = readEnumAllowSkip(EType.class, "Type (ACOUSTIC, ELECTRIC) (Press Enter to skip)");

        EWood topWood = readEnumAllowSkip(EWood.class,
                "Top Wood (INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA) (Press Enter to skip)");

        EWood backWood = readEnumAllowSkip(EWood.class,
                "Back Wood (INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO, CEDAR, ADIRONDACK, ALDER, SITKA) (Press Enter to skip)");

        return new GuitarSpec(builder, model, type, backWood, topWood);
    }

    private <T extends Enum<T>> T readEnumAllowSkip(Class<T> enumType, String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().toUpperCase().trim();
            if (input.isEmpty()) return null;
            try {
                return Enum.valueOf(enumType, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid entry. Press Enter to skip or enter valid.");
            }
        }
    }
}
