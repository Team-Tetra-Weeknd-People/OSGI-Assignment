package sa_osgi_toysservice;

import java.sql.*;
import java.util.*;

import sa_osgi_mall_database.*;

public class ToysServiceImpl implements ToysService {
	
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preState = null;
	private  MallDB mallDB;
	private ResultSet resultSet;
	boolean status;
	
	ArrayList<ToysModel> toysList = new ArrayList<ToysModel>();
	ArrayList<ToysModel> toys = new ArrayList<ToysModel>();
	
	public ToysServiceImpl() {
		mallDB = (MallDB)new MallDBImpl();
		connection = mallDB.connection();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM toy";
			resultSet = statement.executeQuery(SelectAll);
			
			while(resultSet.next()) {
				ToysModel toy = new ToysModel();
				toy.setId(resultSet.getInt("id"));
				toy.setType(resultSet.getString("type"));
				toy.setAgeGroup(resultSet.getString("ageGroup"));
				toy.setPrice(resultSet.getDouble("price"));
				toysList.add(toy);
			}	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		this.deleteToy(6);
	}

	@Override
	public boolean checkToysAvailability(String type, String ageGroup) {
		status = false;
		toys.removeAll(toys);
		
		for(ToysModel oneToy : toysList) {
			if (oneToy.getType().equalsIgnoreCase(type) && oneToy.getAgeGroup().equalsIgnoreCase(ageGroup)) {
				toys.add(oneToy);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkToysByType(String type) {
		status = false;
		toys.removeAll(toys);
		
		for(ToysModel oneToy : toysList) {
			if (oneToy.getType().equalsIgnoreCase(type)) {
				toys.add(oneToy);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkToysByAgeGroup(String ageGroup) {
		status = false;
		toys.removeAll(toys);
		
		for(ToysModel oneToy : toysList) {
			if (oneToy.getAgeGroup().equalsIgnoreCase(ageGroup)) {
				toys.add(oneToy);
				status = true;
			}
		}
		return status;
	}


	@Override
	public ArrayList<ToysModel> getAlltoys() {
		// TODO Auto-generated method stub
		return toysList;
	}

	@Override
	public ArrayList<ToysModel> gettoys() {
		// TODO Auto-generated method stub
		return toys;
	}

	@Override
	public ArrayList<ToysModel> getToysAll() {
		ArrayList<ToysModel> allToys = new ArrayList<ToysModel>();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM toy";
			resultSet = statement.executeQuery(SelectAll);
			
			while(resultSet.next()) {
				ToysModel toy = new ToysModel();
				toy.setId(resultSet.getInt("id"));
				toy.setType(resultSet.getString("type"));
				toy.setAgeGroup(resultSet.getString("ageGroup"));
				toy.setPrice(resultSet.getDouble("price"));
				allToys.add(toy);
			}	
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return allToys;
	}

	@Override
	public ToysModel getOneToy(int id) {
		ToysModel toy = new ToysModel();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM toy WHERE id = " + id;
			resultSet = statement.executeQuery(SelectAll);
			

			if(resultSet.next() == false) {
				return null;
			}
			else {
				toy.setId(resultSet.getInt("id"));
				toy.setType(resultSet.getString("type"));
				toy.setAgeGroup(resultSet.getString("ageGroup"));
				toy.setPrice(resultSet.getDouble("price"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return toy;
	}

	@Override
	public void insertToy(String type, String ageGroup, double price) {
		try {
			preState = connection.prepareStatement("INSERT INTO toy values (null,?,?,?)");
			preState.setString(1, type);
			preState.setString(2, ageGroup);
			preState.setDouble(3, price);
			preState.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
		

	@Override
	public void updateToy(int id, String type, String ageGroup, double price) {
		try {
			preState = connection.prepareStatement("UPDATE toy set type = (?) , ageGroup = (?) , price = (?) WHERE id = (?)");
			preState.setString(1, type);
			preState.setString(2, ageGroup);
			preState.setDouble(3, price);
			preState.setInt(4, id);
			preState.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void deleteToy(int id) {
		try {
			statement=connection.createStatement();
			String DeleteOne = "DELETE FROM toy WHERE id = " + id;
			statement.executeUpdate(DeleteOne);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
		
	}
	
	

