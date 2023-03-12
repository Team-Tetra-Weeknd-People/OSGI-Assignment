package com.mtit.vehiclepart.service;

public class Fluid extends VehiclePart{
	
	private String brand;
	private String volume;
	
	public Fluid(String name, String brand, String volume, double price) {
		super(name, price);
		this.brand = brand;
		this.volume = volume;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
}
