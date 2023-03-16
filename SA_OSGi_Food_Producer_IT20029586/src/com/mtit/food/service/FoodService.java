package com.mtit.food.service;

import java.util.ArrayList;

public interface FoodService {

	public boolean checkFoodAvailability(String name, String type);
	public boolean checkFoodByName(String name);
	public boolean checkFoodByType(String type);
	
	public ArrayList<Food> getAllFoods();
	public ArrayList<Food> getFoods();
	
	public ArrayList<Food> getAll();
	public Food getOne(int id);
	public void insert(String name, String type, double price);
	public void update(int id,String name, String type, double price);
	public void delete(int id);
}
