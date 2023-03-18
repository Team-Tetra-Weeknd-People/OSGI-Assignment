package sa_osgi_clothingservice;

import java.util.ArrayList;

public interface ClothingService {
	
	public boolean checkClothingAvailability(String brandName,String type,String Size);
	public boolean checkClothingAvailabilityBrand(String brandName);
	public boolean checkClothingAvailabilityType(String type);
	public boolean checkClothingAvailabilitySize(String Size);
	public boolean checkClothingAvailabilityBT(String brandName,String type);
	public boolean checkClothingAvailabilityTS(String type,String Size);
	public boolean checkClothingAvailabilityBS(String brandName,String Size);
	public double price(); 
	public ArrayList<Clothing> results();
	public int getCount();
	public ArrayList<Clothing> getAllClothes();
	public void insertDataToDB(String brand,String type,String size,double price);
	public void updateDB(Integer id,String brand,String type, String size,double price);
	public void deleteFromDB(int id);
	public Clothing getClothingFromID(int id);
}