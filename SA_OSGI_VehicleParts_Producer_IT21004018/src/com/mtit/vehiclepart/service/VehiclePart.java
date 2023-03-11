package com.mtit.vehiclepart.service;

public abstract class VehiclePart {
	
	protected String name;
	protected double price;
	
	public VehiclePart(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
