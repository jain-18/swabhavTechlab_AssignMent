package com.techlab.guitar.model;

import com.techlab.guitar.model.*;

public class GuitarSpec {
	private EBuilder builder;
	@Override
	public String toString() {
		return "GuitarSpec [builder=" + builder + ", model=" + model + ", type=" + type + ", backWood=" + backWood
				+ ", topWood=" + topWood + "]";
	}

	private String model;
	private EType type;
	private EWood backWood;
	private EWood topWood;

	public EBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(EBuilder builder) {
		this.builder = builder;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public EType getType() {
		return type;
	}

	public void setType(EType type) {
		this.type = type;
	}

	public EWood getBackWood() {
		return backWood;
	}

	public void setBackWood(EWood backWood) {
		this.backWood = backWood;
	}

	public EWood getTopWood() {
		return topWood;
	}

	public void setTopWood(EWood topWood) {
		this.topWood = topWood;
	}

	public GuitarSpec(EBuilder builder, String model, EType type, EWood backWood, EWood topWood) {
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
	}

}
