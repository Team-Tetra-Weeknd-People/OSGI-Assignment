package com.mtit.vehiclepart.service;

public class VehiclePartsServiceImpl implements VehiclePartsService {

	Part pt1 = new Part("Front-Bumper", "Toyota" , "Premio" , 46500.00 );
	Part pt2 = new Part("Rear-Bumper", "Toyota" , "Aqua" , 42500.00 );
	Part pt3 = new Part("Headlights", "BMW" , "318i" , 19200.00 );
	Part pt4 = new Part("Brakelights", "BMW" , "530e" , 268000.00 );
	Part pt5 = new Part("Side-Mirrors", "Nissan" , "Leaf" , 36300.00 );
	
	Part[] stockParts = {pt1 , pt2 , pt3 , pt4 , pt5};
	
	Paint pn1 = new Paint("White", "2", "4L", 20000);
	Paint pn2 = new Paint("White", "2", "2L", 10000);
	Paint pn3 = new Paint("White", "1", "2L", 12000);
	Paint pn4 = new Paint("Black", "2", "2L", 14000);
	Paint pn5 = new Paint("Black", "1", "4L", 32000);
	Paint pn6 = new Paint("Blue", "3", "2L", 8000);
	Paint pn7 = new Paint("Blue", "2" , "4L", 18000);
	
	Paint[] stockPaints = {pn1 , pn2 , pn3 , pn4 , pn5 , pn6 , pn7};
	
	Fluid fl1 = new Fluid("Brake-Oil", "Caltex" , "500ml" , 2800);
	Fluid fl2 = new Fluid("Brake-Oil", "Toyota" , "250ml" , 2200);
	Fluid fl3 = new Fluid("Engine-Oil", "Caltex" , "4L" , 14000);
	Fluid fl4 = new Fluid("Engine-Oil", "Toyota" , "4L" , 18000);
	Fluid fl5 = new Fluid("Transmission-Oil", "Toyota" , "4L" , 28000);
	Fluid fl6 = new Fluid("Coolant" , "Toyota" , "4L" , 28000);
	
	Fluid[] stockFluids = {fl1 , fl2 , fl3 , fl4 , fl5 , fl6};
	
	Part[] parts = new Part[100];
	Paint[] paints = new Paint[100];
	Fluid[] fluids = new Fluid[100];
	
	boolean status;
	int count;
	
	
	@Override
	public boolean checkPartAvailability(String name, String brand, String model) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getName().equalsIgnoreCase(name) && stockParts[i].getBrand().equalsIgnoreCase(brand) && stockParts[i].getModel().equalsIgnoreCase(model)) {
				parts[count++] = stockParts[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintAvailability(String colorName, String colorCode, String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getName().equalsIgnoreCase(colorName) && stockPaints[i].getColorCode().equals(colorCode) && stockPaints[i].getVolume().equalsIgnoreCase(volume)) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidAvailability(String name, String brand , String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getName().equalsIgnoreCase(name) && stockFluids[i].getBrand().equalsIgnoreCase(brand) && stockFluids[i].getVolume().equalsIgnoreCase(volume)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPartByNameNBrand(String name, String brand) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getName().equalsIgnoreCase(name) && stockParts[i].getBrand().equalsIgnoreCase(brand)) {
				parts[count++] = stockParts[i];
				System.out.println(parts[i].getName());
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPartByBrandNModel(String brand, String model) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getBrand().equalsIgnoreCase(brand) && stockParts[i].getModel().equalsIgnoreCase(model)) {
				parts[count++] = stockParts[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPartByNameNModel(String name, String model) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getName().equalsIgnoreCase(name) && stockParts[i].getModel().equalsIgnoreCase(model)) {
				parts[count++] = stockParts[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPartByName(String name) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getName().equalsIgnoreCase(name)) {
				parts[count++] = stockParts[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPartByBrand(String brand) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getBrand().equalsIgnoreCase(brand)) {
				parts[count++] = stockParts[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPartByModel(String model) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockParts.length ; i++) {
			if (stockParts[i].getModel().equalsIgnoreCase(model)) {
				parts[count++] = stockParts[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintByColorNColorCode(String colorName, String colorCode) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getName().equalsIgnoreCase(colorName) && stockPaints[i].getColorCode().equals(colorCode)) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintByColorNVolume(String colorName, String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getName().equalsIgnoreCase(colorName) && stockPaints[i].getVolume().equalsIgnoreCase(volume)) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintByColorCodeNVolume(String colorCode, String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getColorCode().equals(colorCode) && stockPaints[i].getVolume().equalsIgnoreCase(volume)) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintByColor(String colorName) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getName().equalsIgnoreCase(colorName)) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintByColorCode(String colorCode) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getColorCode() == colorCode) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkPaintByVolume(String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for(int i = 0 ; i < stockPaints.length ; i++) {
			if(stockPaints[i].getVolume().equalsIgnoreCase(volume)) {
				paints[count++] = stockPaints[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidByNameNBrand(String name, String brand) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getName().equalsIgnoreCase(name) && stockFluids[i].getBrand().equalsIgnoreCase(brand)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidByNameNVolume(String name, String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getName().equalsIgnoreCase(name) && stockFluids[i].getVolume().equalsIgnoreCase(volume)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidByBrandNVolume(String brand, String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getBrand().equalsIgnoreCase(brand) && stockFluids[i].getVolume().equalsIgnoreCase(volume)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidByName(String name) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getName().equalsIgnoreCase(name)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidByBrand(String brand) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getBrand().equalsIgnoreCase(brand)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public boolean checkFluidByVolume(String volume) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
		
		for (int i = 0 ; i < stockFluids.length ; i++) {
			if (stockFluids[i].getVolume().equalsIgnoreCase(volume)) {
				fluids[count++] = stockFluids[i];
				status = true;
			}
		}
		return status;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	
	@Override
	public Fluid[] getFluids() {
		// TODO Auto-generated method stub
		return fluids;
	}
	
	@Override
	public Paint[] getPaints() {
		// TODO Auto-generated method stub
		return paints;
	}
	
	@Override
	public Part[] getParts() {
		// TODO Auto-generated method stub
		return parts;
	}

	@Override
	public Fluid[] getAllFluids() {
		// TODO Auto-generated method stub
		return stockFluids;
	}

	@Override
	public Paint[] getAllPaints() {
		// TODO Auto-generated method stub
		return stockPaints;
	}

	@Override
	public Part[] getAllParts() {
		// TODO Auto-generated method stub
		return stockParts;
	}

	
}
