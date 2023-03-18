package sa_osgi_clothingservice;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

import sa_osgi_mall_database.MallDBImpl;

public class ClothingServiceImpl implements ClothingService{
	
	private Connection connection = null;
	private Statement statement = null;
	private MallDBImpl mallDB;
	private ResultSet resultSet;
	
	
	public ClothingServiceImpl() {
		super();
		mallDB = (MallDBImpl) new MallDBImpl();
		connection = mallDB.connection();
	}
	public ArrayList<Clothing> getAllClothes() {
		// TODO Auto-generated method stub
		ArrayList<Clothing> clothings = new ArrayList<Clothing>();
		try {
			statement = connection.createStatement();
			String SelectAll = "SELECT * FROM clothingitem";
			resultSet = statement.executeQuery(SelectAll);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			while(resultSet.next()) {
				Clothing clothing = new Clothing();
				
				clothing.setId(resultSet.getInt("id"));
				clothing.setBrand(resultSet.getString("brand"));
				clothing.setType(resultSet.getString("type"));
				clothing.setSize(resultSet.getString("size"));
				clothing.setPrice(resultSet.getDouble("price"));
				
				clothings.add(clothing);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clothings;
	}
	
	public void insertDataToDB(String brand,String type,String size,double price) {
		try {
			statement = connection.createStatement();
			String insert = "INSERT INTO `clothingitem` (`id`, `brand`, `type`, `size`, `price`) VALUES (NULL, '"+brand+"', '"+type+"', '"+size+"', "+price+")";
			statement.executeUpdate(insert);
			System.out.println("New Clothing Item Added to the System!!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	Clothing c1 = new Clothing("Nike", "T-Shirt", "M", 9000.00);
//	Clothing c2 = new Clothing("Polo", "Shirt", "S", 10500.00);
//	Clothing c3 = new Clothing("Adidas", "T-Shirt", "L", 7800.00);
//	Clothing c4 = new Clothing("Moose", "Trouser", "32", 4590.00);
//	Clothing c5 = new Clothing("Nike", "T-Shirt", "L", 19000.00);
//	
	
	ArrayList<Clothing> inStockClothes = new ArrayList<Clothing>();
	ArrayList<Clothing> clothes = new ArrayList<Clothing>();
	
	
	double outPrice;
	
//	Clothing[] clothes = new Clothing[100];
	
	int count ;
	
	@Override
	public boolean checkClothingAvailability(String brandName, String type, String Size) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if (inStockClothes.get(i).getBrand().equalsIgnoreCase(brandName)
					&& inStockClothes.get(i).getType().equalsIgnoreCase(type)
					&& inStockClothes.get(i).getSize().equalsIgnoreCase(Size)) {	
					clothes.add(inStockClothes.get(i));
					count++;
			}
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return outPrice;
	}
	@Override
	public boolean checkClothingAvailabilityBrand(String brandName) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getBrand().equalsIgnoreCase(brandName)) {	
				clothes.add(inStockClothes.get(i));
				count++;
			}		
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkClothingAvailabilityType(String type) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getType().equalsIgnoreCase(type)) {
				clothes.add(inStockClothes.get(i));
				count++;
			}		
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkClothingAvailabilitySize(String Size) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getSize().equalsIgnoreCase(Size)){
				clothes.add(inStockClothes.get(i));
				count++;
			}		
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean checkClothingAvailabilityBT(String brandName, String type) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getBrand().equalsIgnoreCase(brandName)
					&& inStockClothes.get(i).getType().equalsIgnoreCase(type)) {
						clothes.add(inStockClothes.get(i));
				count++;
			}		
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkClothingAvailabilityTS(String type, String Size) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getSize().equalsIgnoreCase(Size)
					&& inStockClothes.get(i).getType().equalsIgnoreCase(type)) {
				clothes.add(inStockClothes.get(i));
				count++;
			}		
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean checkClothingAvailabilityBS(String brandName, String Size) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		count = 0;
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getBrand().equalsIgnoreCase(brandName)
					&& inStockClothes.get(i).getSize().equalsIgnoreCase(Size)) {
				clothes.add(inStockClothes.get(i));
				count++;
			}		
		}
		if (count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ArrayList<Clothing> results() {
		// TODO Auto-generated method stub
		return clothes;
	}
	public int getCount() {
		return count;
	}
	@Override
	public Clothing getClothingFromID(int id) {
		// TODO Auto-generated method stub
		inStockClothes = getAllClothes();
		for(int i = 0; i < inStockClothes.size();i++) {
			if ( inStockClothes.get(i).getId()==id) {
				return inStockClothes.get(i);
			}		
		}
		return null;
	}
	@Override
	public void updateDB(Integer id, String brand, String type, String size, double price) {
		// TODO Auto-generated method stub
		try {
			statement = connection.createStatement();
			String update1 = "UPDATE clothingitem set brand ='"+brand+"' WHERE id = "+id+"";
			statement.executeUpdate(update1);
			String update2 = "UPDATE clothingitem set type ='"+type+"' WHERE id = "+id+"";
			statement.executeUpdate(update2);
			String update3 = "UPDATE clothingitem set size ='"+size+"' WHERE id = "+id+"";
			statement.executeUpdate(update3);
			String update4 = "UPDATE clothingitem set price ="+price+" WHERE id = "+id+"";
			statement.executeUpdate(update4);
			System.out.println("Clothing Item(ID:"+id+") Updated");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteFromDB(int id) {
		// TODO Auto-generated method stub
		try {
			statement = connection.createStatement();
			String Delete = "DELETE FROM clothingitem WHERE id = "+id+"";
			statement.executeUpdate(Delete);
			System.out.println("Clothing Item(ID:"+id+") Deleted!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
