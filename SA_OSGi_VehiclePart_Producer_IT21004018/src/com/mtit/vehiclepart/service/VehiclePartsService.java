package com.mtit.vehiclepart.service;

import java.util.ArrayList;

public interface VehiclePartsService {

	public boolean checkPartAvailability(String name, String brand, String model);
	public boolean checkPartByNameNBrand(String name, String brand);
	public boolean checkPartByBrandNModel(String brand, String model);
	public boolean checkPartByNameNModel(String name , String model);
	public boolean checkPartByName(String name);
	public boolean checkPartByBrand(String brand);
	public boolean checkPartByModel(String model);
	
	public int getLength();
	public ArrayList<Part> getAllParts();
	public ArrayList<Part> getParts();
}
