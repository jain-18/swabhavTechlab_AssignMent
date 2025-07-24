package com.techlab.guitar.model;

public enum EWood {
	INDIAN_ROSEWOOD,BRAZILIAN_ROSEWOOD,MAHOGANY,MAPLE,COCOBOLO,CEDAR,ADIRONDACK,ALDER,SITKA;
	
	public String toString(EWood wood) {
		switch(wood) {
		case INDIAN_ROSEWOOD : return "Indian Rosewood";
		case BRAZILIAN_ROSEWOOD : return "Brazilian Rosewood";
		case MAHOGANY : return "Mahogany";
		case MAPLE : return "Maple";
		case COCOBOLO : return "Cocobolo";
		case CEDAR : return "Cedar";
		case ADIRONDACK : return "Adirondack";
		case ALDER : return "Alder";
		case SITKA : return "Sitka";
		default : return "";
		}
	}
}
