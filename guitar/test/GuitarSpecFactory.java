package com.techlab.guitar.test;

import com.techlab.guitar.model.EBuilder;
import com.techlab.guitar.model.EType;
import com.techlab.guitar.model.EWood;
import com.techlab.guitar.model.GuitarSpec;

public class GuitarSpecFactory {
	private static GuitarSpecInputReader reader = new GuitarSpecInputReader();

    public static GuitarSpec createGuitarSpec() {
        String model = reader.readModel();
        EBuilder builder = reader.readEnum(EBuilder.class, 
            "Enter builder: FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY");

        System.out.println();
        EType type = reader.readEnum(EType.class, "Enter type: ACOUSTIC, ELECTRIC");

        System.out.println();
        EWood topWood = reader.readEnum(EWood.class, 
            "Enter top wood: INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO,\nCEDAR, ADIRONDACK, ALDER, SITKA");

        System.out.println();
        EWood backWood = reader.readEnum(EWood.class, 
            "Enter back wood: INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO,\nCEDAR, ADIRONDACK, ALDER, SITKA");

        return new GuitarSpec(builder, model, type, backWood, topWood);
    }
}
