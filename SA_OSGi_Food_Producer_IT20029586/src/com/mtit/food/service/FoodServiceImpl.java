package com.mtit.food.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import sa_osgi_mall_database.*;

public class FoodServiceImpl implements FoodService {
	
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preState = null;
	private MallDB mallDB;
	private ResultSet resultSet;
	boolean status;
	
	ArrayList<Food> foodList = new ArrayList<>();
	ArrayList<Food> foods = new ArrayList<>();
	
	public FoodServiceImpl() {
		mallDB = (MallDB)new MallDBImpl();
		connection = mallDB.connection();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "Select * FROM food";
			resultSet = statement.executeQuery(SelectAll);
			
			while(resultSet.next()) {
				Food food = new Food();
				food.setId(resultSet.getInt("id"));
				food.setName(resultSet.getString("name"));
				food.setType(resultSet.getString("type"));
				food.setPrice(resultSet.getDouble("price"));
				foodList.add(food);
			}	
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		this.delete(6);
		
	}

	@Override
	public boolean checkFoodAvailability(String name, String type) {
		// TODO Auto-generated method stub
		status = false;
		foods.removeAll(foods);
		
		for(Food oneFood : foodList) {
			if(oneFood.getName().equalsIgnoreCase(name) && oneFood.getType().equalsIgnoreCase(type)) {
				foods.add(oneFood);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkFoodByName(String name) {
		// TODO Auto-generated method stub
		status = false;
		foods.removeAll(foods);
		
		for(Food oneFood : foodList) {
			if(oneFood.getName().equalsIgnoreCase(name)) {
				foods.add(oneFood);
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean checkFoodByType(String type) {
		// TODO Auto-generated method stub
		status = false;
		foods.removeAll(foods);
		
		for(Food oneFood : foodList) {
			if(oneFood.getType().equalsIgnoreCase(type)) {
				foods.add(oneFood);
				status = true;
			}
		}
		return status;
	}

	@Override
	public ArrayList<Food> getAllFoods() {
		// TODO Auto-generated method stub
		return foodList;
	}

	@Override
	public ArrayList<Food> getFoods() {
		// TODO Auto-generated method stub
		return foods;
	}

	@Override
	public ArrayList<Food> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Food> allFoods = new ArrayList<Food>();
		
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM food";
			resultSet = statement.executeQuery(SelectAll);
			
			while(resultSet.next()) {
				Food food = new Food();
				food.setId(resultSet.getInt("id"));
				food.setName(resultSet.getString("name"));
				food.setType(resultSet.getString("type"));
				food.setPrice(resultSet.getDouble("price"));
				allFoods.add(food);
			}	
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return allFoods;
	}

	@Override
	public Food getOne(int id) {
		// TODO Auto-generated method stub
		Food food = new Food();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM food WHERE id = " + id;
			resultSet = statement.executeQuery(SelectAll);
			

			if(resultSet.next() == false) {
				return null;
			}
			else {
				food.setId(resultSet.getInt("id"));
				food.setName(resultSet.getString("name"));
				food.setType(resultSet.getString("type"));
				food.setPrice(resultSet.getDouble("price"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return food;
	}

	@Override
	public void insert(String name, String type, double price) {
		// TODO Auto-generated method stub
		try {
			preState = connection.prepareStatement("INSERT INTO food values (null,?,?,?)");
			preState.setString(1, name);
			preState.setString(2, type);
			preState.setDouble(3, price);
			preState.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void update(int id, String name, String type, double price) {
		// TODO Auto-generated method stub
		try {
			preState = connection.prepareStatement("UPDATE food set name = (?) , type = (?) , price = (?) WHERE id = (?)");
			preState.setString(1, name);
			preState.setString(2, type);
			preState.setDouble(3, price);
			preState.setInt(4, id);
			preState.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			statement=connection.createStatement();
			String DeleteOne = "DELETE FROM food WHERE id = " + id;
			statement.executeUpdate(DeleteOne);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
