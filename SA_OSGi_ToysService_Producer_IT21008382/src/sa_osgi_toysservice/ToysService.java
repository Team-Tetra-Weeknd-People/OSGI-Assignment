package sa_osgi_toysservice;

import java.util.ArrayList;

public interface ToysService {
	
	public boolean checkToysAvailability(String type, String ageGroup);
	public boolean checkToysByType(String type);
	public boolean checkToysByAgeGroup(String ageGroup);
	

	public ArrayList<ToysModel> getAlltoys();
	public ArrayList<ToysModel> gettoys();
	
	public ArrayList<ToysModel> getToysAll();
	public ToysModel getOneToy(int id);
	public void insertToy(String type, String ageGroup, double price);
	public void updateToy(int id,String type, String ageGroup, double price);
	public void deleteToy(int id);
	
	
}
