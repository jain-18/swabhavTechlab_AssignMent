package com.techlab.guitar.model;

public enum EType {
	ACOUSTIC, ELECTRIC;

	public String toString(EType type) {
		switch (type) {
		case ACOUSTIC:
			return "Acoustic";
		case ELECTRIC:
			return "Electric";
		default:
			return "";

		}
	}
}
