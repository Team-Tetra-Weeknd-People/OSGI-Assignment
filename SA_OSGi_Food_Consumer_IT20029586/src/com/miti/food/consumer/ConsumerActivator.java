package com.miti.food.consumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.food.service.Food;
import com.mtit.food.service.FoodService;

public class ConsumerActivator implements BundleActivator {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	String name;
	String type;
	boolean status;
	boolean valid;
	boolean allEmpty;
	String exit;

	public void start(BundleContext bundleContext) throws Exception {
		
		serviceRef = bundleContext.getServiceReference(FoodService.class.getName());
		@SuppressWarnings("unchecked")
		FoodService foodService = (FoodService)bundleContext.getService(serviceRef);
		
		ArrayList<Food> foodList = new ArrayList<>();
		ArrayList<Food> foods = new ArrayList<>();
		
		System.out.print("---------------------");
		System.out.print("Welcome to the Caffeteria");
		System.out.println("---------------------\n");
		
		System.out.println("---Enter the required details you need to get the items you need---");
		System.out.println("---------------Enter 2 Blanks to exit from the system--------------");
		
		foodList = foodService.getAll();
		
		try {
			while(true) {
				name = "";
				type = "";
				status = false;
				allEmpty = false;
				valid = false;
				foods.removeAll(foods);
				
				status = true;
				System.out.print("Enter the Food Name : ");
				name = br.readLine();
				
				if(name.length() != 0) {
					while(status) {
						for(Food food : foodList) {
							if(food.getName().equalsIgnoreCase(name)) {
								valid = true;
								break;
							}
						}
						if(valid) {
							break;
						}
						System.out.println("Invalid Input !!\n");
						System.out.print("Enter the Food Name : ");
						name = br.readLine();
						if(name.length() == 0) {
							break;
						}
					}
				}
				
				status = true;
				valid = false;
				System.out.print("Enter the Food Type : ");
				type = br.readLine();
				
				if(type.length() != 0) {
					while(status) {
						for(Food food : foodList) {
							if(food.getType().equalsIgnoreCase(type)) {
								valid = true;
								break;
							}
						}
						if(valid) {
							break;
						}
						System.out.println("Invalid Input !!\n");
						System.out.print("Enter the Food Type : ");
						type = br.readLine();
						if(type.length() == 0) {
							break;
						}
					}
				}
				status = false;
				
				if(name.length() != 0 && type.length() != 0) {
					status = foodService.checkFoodAvailability(name, type);
				}
				else if(name.length() != 0) {
					status = foodService.checkFoodByName(name);
				}
				else if(type.length() != 0) {
					status = foodService.checkFoodByType(type);
				}
				else {
					allEmpty = true;
				}
				
				if(status) {
					foods = foodService.getFoods();
					System.out.println("\n-------------------------------------");
					for(Food onePart : foods) {
						System.out.println("Name - " + onePart.getName());
						System.out.println("Type - " + onePart.getType());
						System.out.println("Price - Rs." + decfor.format(onePart.getPrice()));
						System.out.println("-------------------------------------");
					}
				}
				else if (allEmpty){
					System.out.println("");
					System.out.println("-------------------------------------");
					System.out.println("No Items has been selected");
					System.out.println("-------------------------------------\n");
					System.out.print("Do you want to leave? (Y | N) : ");
					exit = br.readLine();
					
					if(exit.equalsIgnoreCase("Y") || exit.equalsIgnoreCase("Yes")) {
						this.stop(bundleContext);
						break;
					}
				}
				else{
					System.out.println("");
					System.out.println("-------------------------------------");
					System.out.println("Item(s) you are looking for is/are Currently not available.");
					System.out.println("-------------------------------------");
				}
				System.out.println("");
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Bon Appétit!!");
		bundleContext.ungetService(serviceRef);
	}

}
