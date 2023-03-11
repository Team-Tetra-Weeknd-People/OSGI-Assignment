package com.mtit.vehiclepart.service;

public class Part extends VehiclePart{
	
	private String brand;
	private String model;
	
	public Part(String name, String brand, String model, double price) {
		super(name, price);
		this.brand = brand;
		this.model = model;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
