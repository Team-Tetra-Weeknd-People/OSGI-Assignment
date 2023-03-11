package com.mtit.vehiclepart.service;

public interface VehiclePartsService {
	
	public boolean checkPartAvailability(String name, String brand, String model);
	public boolean checkPaintAvailability(String colorName, String colorCode, String volume);
	public boolean checkFluidAvailability(String name, String brand , String volume);
	
	public boolean checkPartByNameNBrand(String name, String brand);
	public boolean checkPartByBrandNModel(String brand, String model);
	public boolean checkPartByNameNModel(String name , String model);
	public boolean checkPartByName(String name);
	public boolean checkPartByBrand(String brand);
	public boolean checkPartByModel(String model);
	
	public boolean checkPaintByColorNColorCode(String colorName, String colorCode);
	public boolean checkPaintByColorNVolume(String colorName, String volume);
	public boolean checkPaintByColorCodeNVolume(String colorCode , String volume);
	public boolean checkPaintByColor(String colorName);
	public boolean checkPaintByColorCode(String colorCode);
	public boolean checkPaintByVolume(String volume);
	
	public boolean checkFluidByNameNBrand(String name, String brand);
	public boolean checkFluidByNameNVolume(String name, String volume);
	public boolean checkFluidByBrandNVolume(String brand, String volume);
	public boolean checkFluidByName(String name);
	public boolean checkFluidByBrand(String brand);
	public boolean checkFluidByVolume(String volume);
	
	public int getCount();
	
	public Fluid[] getFluids();
	public Paint[] getPaints();
	public Part[] getParts();
	
	public Fluid[] getAllFluids();
	public Paint[] getAllPaints();
	public Part[] getAllParts();
}
