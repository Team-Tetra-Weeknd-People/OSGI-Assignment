package com.mtit.vehiclepart.service;

public class Paint extends VehiclePart{
	
	private String colorCode;
	private String volume;
	
	public Paint(String name, String colorCode, String volume, double price) {
		super(name, price);
		this.colorCode = colorCode;
		this.volume = volume;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
}
