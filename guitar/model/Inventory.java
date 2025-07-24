package com.techlab.guitar.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
	private List<Guitar> guitars;

	public Inventory() {
		guitars = new ArrayList<>();
	}

	public void add(Guitar guitar) {
		guitars.add(guitar);
	}

	public List<Guitar> getAll() {
		return guitars;
	}

	public List<Guitar> search(GuitarSpec searchSpec) {
		List<Guitar> matchingGuitars = new ArrayList<>();
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if (guitarSpec.getBuilder() != searchSpec.getBuilder()) {
				continue;
			}
			String model = searchSpec.getModel().toLowerCase();
			if ((model != null) && (!model.equals("") && (!model.equals(guitarSpec.getModel().toLowerCase())))) {
				continue;
			}
			if (searchSpec.getType() != guitarSpec.getType()) {
				continue;
			}
			if (searchSpec.getBackWood() != guitarSpec.getBackWood()) {
				continue;
			}
			if (searchSpec.getTopWood() != guitarSpec.getTopWood()) {
				continue;
			}
			matchingGuitars.add(guitar);
		}
		return matchingGuitars;
	}
	
	public List<Guitar> searchGuitar(GuitarSpec searchSpec) {
        return guitars.stream()
                .filter(g -> matchesFlexible(searchSpec, g.getSpec()))
                .collect(Collectors.toList());
    }

    private boolean matchesFlexible(GuitarSpec searchSpec, GuitarSpec guitarSpec) {
        if (searchSpec.getBuilder() != null && searchSpec.getBuilder() != guitarSpec.getBuilder()) {
            return false;
        }
        if (searchSpec.getModel() != null && !searchSpec.getModel().equalsIgnoreCase(guitarSpec.getModel())) {
            return false;
        }
        if (searchSpec.getType() != null && searchSpec.getType() != guitarSpec.getType()) {
            return false;
        }
        if (searchSpec.getBackWood() != null && searchSpec.getBackWood() != guitarSpec.getBackWood()) {
            return false;
        }
        if (searchSpec.getTopWood() != null && searchSpec.getTopWood() != guitarSpec.getTopWood()) {
            return false;
        }
        return true;
    }

	public void deleteGuitar(String serialNumber) {
		int check = 0;
		for (int i = 0; i < guitars.size(); i++) {
			if(guitars.get(i).getSerialNumber().compareTo(serialNumber)==0) {
				guitars.remove(i);
				System.out.println("Guite deleted successfully!!");
				check =1;
			}
		}
		if(check == 0) {
			System.out.println("There is no guitar with this serial number to delete");
		}
	}
	
	public int containsSerialNumber(String serialNumber) {
		for (int i = 0; i < guitars.size(); i++) {
			if(guitars.get(i).getSerialNumber().compareTo(serialNumber)==0) {
				System.out.println("There is already guitar with this serial number");
				return 1;
			}
		}
		return 0;
	}
}
