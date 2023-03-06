package sa_osgi_clothingservice;

public interface ClothingService {
	
	public boolean checkClothingAvailability(String brandName,String type,String Size);
	public boolean checkClothingAvailabilityBrand(String brandName);
	public boolean checkClothingAvailabilityType(String type);
	public boolean checkClothingAvailabilitySize(String Size);
	public boolean checkClothingAvailabilityBT(String brandName,String type);
	public boolean checkClothingAvailabilityTS(String type,String Size);
	public boolean checkClothingAvailabilityBS(String brandName,String Size);
	public double price(); 
}
