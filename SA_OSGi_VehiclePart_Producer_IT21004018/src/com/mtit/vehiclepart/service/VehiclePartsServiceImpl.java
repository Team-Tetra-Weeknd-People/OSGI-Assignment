package com.mtit.vehiclepart.service;

import java.sql.*;
import java.util.*;

import sa_osgi_mall_database.*;

public class VehiclePartsServiceImpl implements VehiclePartsService {
	
	private Connection connection = null;
	private Statement statement = null;
	private  MallDB mallDB;
	private ResultSet resultSet;
	private int length;
	boolean status;
	int count;
	
	ArrayList<Part> partList = new ArrayList<Part>();
	ArrayList<Part> parts = new ArrayList<Part>();
	
	public VehiclePartsServiceImpl() {
		mallDB = (MallDB)new MallDBImpl();
		connection = mallDB.connection();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "Select * FROM part";
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
	}
	

	@Override
	public boolean checkPartAvailability(String name, String brand, String model) {
		// TODO Auto-generated method stub
		status = false;
		count = 0;
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
		count = 0;
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
		count = 0;
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
		count = 0;
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
		count = 0;
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
		count = 0;
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
		count = 0;
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
	public int getLength() {
		// TODO Auto-generated method stub
		return length;
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

	
}
