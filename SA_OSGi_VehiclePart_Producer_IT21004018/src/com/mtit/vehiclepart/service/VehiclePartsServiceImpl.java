package com.mtit.vehiclepart.service;

import java.sql.*;
import java.util.*;

import sa_osgi_mall_database.*;

public class VehiclePartsServiceImpl implements VehiclePartsService {
	
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preState = null;
	private  MallDB mallDB;
	private ResultSet resultSet;
	boolean status;
	
	ArrayList<Part> partList = new ArrayList<Part>();
	ArrayList<Part> parts = new ArrayList<Part>();
	
	public VehiclePartsServiceImpl() {
		mallDB = (MallDB)new MallDBImpl();
		connection = mallDB.connection();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM part";
			resultSet = statement.executeQuery(SelectAll);
			
			while(resultSet.next()) {
				Part part = new Part();
				part.setId(resultSet.getInt("id"));
				part.setName(resultSet.getString("name"));
				part.setBrand(resultSet.getString("brand"));
				part.setModel(resultSet.getString("model"));
				part.setPrice(resultSet.getDouble("price"));
				partList.add(part);
			}	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		this.deletePart(6);
	}
	

	@Override
	public boolean checkPartAvailability(String name, String brand, String model) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getName().equalsIgnoreCase(name) && onePart.getBrand().equalsIgnoreCase(brand) && onePart.getModel().equalsIgnoreCase(model)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkPartByNameNBrand(String name, String brand) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getName().equalsIgnoreCase(name) && onePart.getBrand().equalsIgnoreCase(brand)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkPartByBrandNModel(String brand, String model) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getBrand().equalsIgnoreCase(brand) && onePart.getModel().equalsIgnoreCase(model)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkPartByNameNModel(String name, String model) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getName().equalsIgnoreCase(name) && onePart.getModel().equalsIgnoreCase(model)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkPartByName(String name) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getName().equalsIgnoreCase(name)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkPartByBrand(String brand) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getBrand().equalsIgnoreCase(brand)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkPartByModel(String model) {
		// TODO Auto-generated method stub
		status = false;
		parts.removeAll(parts);
		
		for(Part onePart : partList) {
			if (onePart.getModel().equalsIgnoreCase(model)) {
				parts.add(onePart);
				status = true;
			}
		}
		return status;
	}

	@Override
	public ArrayList<Part> getAllParts() {
		// TODO Auto-generated method stub
		return partList;
	}

	@Override
	public ArrayList<Part> getParts() {
		// TODO Auto-generated method stub
		return parts;
	}

	@Override
	public ArrayList<Part> getPartsAll() {
		// TODO Auto-generated method stub
		ArrayList<Part> allParts = new ArrayList<Part>();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM part";
			resultSet = statement.executeQuery(SelectAll);
			
			while(resultSet.next()) {
				Part part = new Part();
				part.setId(resultSet.getInt("id"));
				part.setName(resultSet.getString("name"));
				part.setBrand(resultSet.getString("brand"));
				part.setModel(resultSet.getString("model"));
				part.setPrice(resultSet.getDouble("price"));
				allParts.add(part);
			}	
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return allParts;
	}

	@Override
	public Part getOnePart(int id) {
		// TODO Auto-generated method stub
		Part part = new Part();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM part WHERE id = " + id;
			resultSet = statement.executeQuery(SelectAll);
			

			if(resultSet.next() == false) {
				return null;
			}
			else {
				part.setId(resultSet.getInt("id"));
				part.setName(resultSet.getString("name"));
				part.setBrand(resultSet.getString("brand"));
				part.setModel(resultSet.getString("model"));
				part.setPrice(resultSet.getDouble("price"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return part;
	}

	@Override
	public void insertPart(String name, String brand, String model, double price) {
		// TODO Auto-generated method stub
		try {
			preState = connection.prepareStatement("INSERT INTO part values (null,?,?,?,?)");
			preState.setString(1, name);
			preState.setString(2, brand);
			preState.setString(3, model);
			preState.setDouble(4, price);
			preState.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void updatePart(int id, String name, String brand, String model, double price) {
		// TODO Auto-generated method stub
		try {
			preState = connection.prepareStatement("UPDATE part set name = (?) , brand = (?) , model = (?) , price = (?) WHERE id = (?)");
			preState.setString(1, name);
			preState.setString(2, brand);
			preState.setString(3, model);
			preState.setDouble(4, price);
			preState.setInt(5, id);
			preState.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void deletePart(int id) {
		// TODO Auto-generated method stub
		try {
			statement=connection.createStatement();
			String DeleteOne = "DELETE FROM part WHERE id = " + id;
			statement.executeUpdate(DeleteOne);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
