package com.mtit.food.admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.miti.food.consumer.ConsumerActivator;
import com.mtit.food.service.Food;
import com.mtit.food.service.FoodService;

public class AdminActivator implements BundleActivator {


	@SuppressWarnings("rawtypes")
	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	public void start(BundleContext bundleContext) throws Exception {
		
		serviceRef = bundleContext.getServiceReference(FoodService.class.getName());
		@SuppressWarnings("unchecked")
		FoodService foodService = (FoodService)bundleContext.getService(serviceRef);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;
		
		try {
			System.out.println("----------------------------------------------------");
			System.out.println(" Welcome to Cafeteria Administrative Panel");
			System.out.println("----------------------------------------------------");
			System.out.println("");
			
			while(true) {
				System.out.println(" Use the below given COMMANDS to use the access the functions");
				System.out.println("");
				System.out.println("-----------------------------------------------------");
				System.out.println("|     Command  |             Action                 |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       V      |     View All Foods           	    |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       O      |     View one Food by ID            |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       I      |     Insert a Food                  |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       U      |     Update a Food by ID            |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       D      |     Delete a Food by ID            |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       C      |     Go To Food Consumer Panel      |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|      STOP    |     STOP the Admin Panel           |");
				System.out.println("-----------------------------------------------------");
				System.out.println("");
				
				System.out.print("Enter Command : ");
				String input = br.readLine().toUpperCase();
				System.out.println("");
				
				switch(input) {
					case "V" : 
						ArrayList<Food> foodList = new ArrayList<Food>();
						foodList = foodService.getAll();
						if (foodList.size() > 0) {
							System.out.println("-----------------------------------------");
							System.out.println("Food Items Registered In The System");
							System.out.println("-----------------------------------------");
							System.out.println("");
							
							for(Food oneFood : foodList) {
								System.out.println("-----------------------------------------");
								System.out.println("ID : " + oneFood.getId());
								System.out.println("Name : " + oneFood.getName());
								System.out.println("Type : " + oneFood.getType());
								System.out.println("Price : Rs." + decfor.format(oneFood.getPrice()));
								System.out.println("-----------------------------------------");
								System.out.println("");
							}
						}else {
							System.out.println("-----------------------------------------");
							System.out.println("No Food Items Registered In The System");
							System.out.println("-----------------------------------------");
						}
						break;
						
					case "O" : 
						Food food = new Food();
						System.out.print("Enter The Required Food Part ID : ");
						String id = br.readLine();
						food = foodService.getOne(Integer.parseInt(id));
						System.out.println("");
						
						if(food == null) {
							System.out.println("-----------------------------------------");
							System.out.println("No Food Items In The System For ID : " + id);
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						else {
							System.out.println("-----------------------------------------");
							System.out.println("ID : " + food.getId());
							System.out.println("Name : " + food.getName());
							System.out.println("Type : " + food.getType());
							System.out.println("Price : Rs." + decfor.format(food.getPrice()));
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						break;
						
					case "I" : 
						System.out.println("--------Enter Details Of The New Food Item -------");
						String name = "";
						while(name.length() == 0) {
							System.out.print("Enter the Food Item Name : ");
							name = br.readLine();
							if(name.length() == 0) {
								System.out.println("Please Enter a Name!!!");
								System.out.println("");
							}
						}
						
						String type = "";
						while(type.length() == 0) {
							System.out.print("Enter the Food Item Type : ");
							type = br.readLine();
							if(type.length() == 0) {
								System.out.println("Please Enter a Type!!!");
								System.out.println("");
							}
						}
						
						String priceInput = "";
						double price = 0;
						while(priceInput.length() == 0) {
							System.out.print("Enter the Food Item Price(In Rupees): ");
							priceInput = br.readLine();
							if(priceInput.length() == 0) {
								System.out.println("Please Enter a Value!!!");
								System.out.println("");
							}else {
								price = Double.parseDouble(priceInput);
							}
						}
						 foodService.insert(name, type, price);
						
						System.out.println("");
						System.out.println("-----------------------------------------");
						System.out.println("|          New Food Item Added          |");
						System.out.println("-----------------------------------------");
						System.out.println("");
						break;
						
					case "U" : 
						System.out.println("--------Update Details Of The Food Item-------");
						String inputID = "";
						food = new Food();
						while(inputID.length() == 0) {
							System.out.print("Enter The ID Of The Food Item That Need To Update : ");
							inputID = br.readLine();
							if(inputID.length() == 0) {
								System.out.println("Please Enter a Value!!!");
								System.out.println("");
							}
							else {
								food = foodService.getOne(Integer.parseInt(inputID));
							}
						}
						
						System.out.println("");
						
						if(food == null) {
							System.out.println("-----------------------------------------");
							System.out.println("No Food Item Registered In The System For ID : " + inputID);
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						else {
							System.out.println("-------------------------------");
							System.out.println("ID : " + food.getId());
							System.out.println("Name : " + food.getName());
							System.out.println("Type : " + food.getType());
							System.out.println("Price : Rs." + decfor.format(food.getPrice()));
							System.out.println("-----------------------------------------");
							System.out.println("");
							
							name = "";
							type = "";
							priceInput = "";						
							
							System.out.println("-------=Keep a BLANK to skip updating an element------");
							
							System.out.print("Enter the Food Item Name : ");
							name = br.readLine();
							if(name.length() == 0) {
								name = food.getName();
							}
							
							System.out.print("Enter the Food Item Type : ");
							type = br.readLine();
							if(type.length() == 0) {
								type = food.getType();
							}
							
							
							System.out.print("Enter the Food Price(In Rupees): ");
							priceInput = br.readLine();
							if(priceInput.length() == 0) {
								price = food.getPrice();
							}
							else {
								price = Double.parseDouble(priceInput);
							}
							
							foodService.update(Integer.parseInt(inputID), name, type, price);
							System.out.println("");
							System.out.println("-----------------------------------------");
							System.out.println("|       Food Item Details Updated        |");
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						break;
						
					case "D" : 
						System.out.println("--------Update Details Of The Food Item-------");
						inputID = "";
						food = new Food();
						while(inputID.length() == 0) {
							System.out.print("Enter The ID Of The Food Item That Need To Delete : ");
							inputID = br.readLine();
							if(inputID.length() == 0) {
								System.out.println("Please Enter a Value!!!");
								System.out.println("");
							}
							else {
								food = foodService.getOne(Integer.parseInt(inputID));
							}
						}
						
						System.out.println("");
						
						if(food == null) {
							System.out.println("-----------------------------------------");
							System.out.println("No Food Item Registered In The System For ID : " + inputID);
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						else {
							System.out.println("-------------------------------");
							System.out.println("ID : " + food.getId());
							System.out.println("Name : " + food.getName());
							System.out.println("Type : " + food.getType());
							System.out.println("Price : Rs." + decfor.format(food.getPrice()));
							System.out.println("-----------------------------------------");
							System.out.println("");
							
							System.out.print("Do You Want To Delete This Food Item (Y | N): ");
							String delete = br.readLine();
							if(delete.equalsIgnoreCase("Y")) {
								foodService.delete(Integer.parseInt(inputID));
								System.out.println("");
								System.out.println("-----------------------------------------");
								System.out.println("|        Food Item Details Deleted       |");
								System.out.println("-----------------------------------------");
								System.out.println("");
							}
							else {
								System.out.println("");
							}
						}
						
						break;
						
					case "C" : 
						ConsumerActivator consumerActivator = new ConsumerActivator();
						consumerActivator.start(bundleContext);
						break;
						
					case "STOP" : 
						exit = true;
						break;
						
					default:
						System.out.println("Invalid Input!! Please Enter A Command From The Given List!!");
						System.out.println("");
						break;
						
					}
					
					if(exit) {
						this.stop(bundleContext);
						break;
					}
				}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		bundleContext.ungetService(serviceRef);
		System.out.println("Admin Shutting Down...");
	}

}
