package sa_osgi_clothingservice;

public class ClothingServiceImpl implements ClothingService{
	
	Clothing c1 = new Clothing("Nike", "T-Shirt", "M", 9000.00);
	Clothing c2 = new Clothing("Polo", "Shirt", "XL", 10500.00);
	Clothing c3 = new Clothing("Adidas", "T-Shirt", "L", 7800.00);
	Clothing c4 = new Clothing("Moose", "Trouser", "32", 4590.00);
	Clothing c5 = new Clothing("Nike", "T-Shirt", "L", 19000.00);
	
	Clothing[] inStockClothes = {c1,c2,c3,c4,c5};
	
	double outPrice;
	@Override
	public boolean checkClothingAvailability(String brandName, String type, String Size) {
		// TODO Auto-generated method stub
		for(int i = 0; i < inStockClothes.length;i++) {
			if (inStockClothes[i].getBrand().equalsIgnoreCase(brandName)
					&& inStockClothes[i].getType().equalsIgnoreCase(type)
					&& inStockClothes[i].getSize().equalsIgnoreCase(Size)) {
				outPrice = inStockClothes[i].getPrice();
				return true;
			}
		}
		return false;
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return outPrice;
	}

	@Override
	public boolean checkClothingAvailabilityBrand(String brandName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkClothingAvailabilityType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkClothingAvailabilitySize(String Size) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkClothingAvailabilityBT(String brandName, String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkClothingAvailabilityTS(String type, String Size) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkClothingAvailabilityBS(String brandName, String Size) {
		// TODO Auto-generated method stub
		return false;
	}

}
