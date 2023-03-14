package com.mtit.vehiclepart.admin;

import com.miti.vehiclepart.comsumer.ConsumerActivator;
import com.mtit.vehiclepart.service.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AdminActivator implements BundleActivator {

	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	@SuppressWarnings("null")
	public void start(BundleContext bundleContext) throws Exception {
		
		serviceRef = bundleContext.getServiceReference(VehiclePartsService.class.getName());
		@SuppressWarnings("unchecked")
		VehiclePartsService vehiclePartService = (VehiclePartsService)bundleContext.getService(serviceRef);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;
		
		try {
			System.out.println("----------------------------------------------------");
			System.out.println(" Welcome to Vehicle Part Store Administrative Panel");
			System.out.println("----------------------------------------------------");
			System.out.println("");
			
			while(true) {
				System.out.println(" Use the below given COMMANDS to use the access the functions");
				System.out.println("");
				System.out.println("-----------------------------------------------------");
				System.out.println("|     Command  |             Action                 |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       Q      |  View All Vehicle Parts            |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       W      |  View one Vehicle Part by ID       |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       E      |  Insert a Vehicle Part             |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       R      |  Update a Vehicle Part by ID       |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       T      |  Delete a Vehicle Part by ID       |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       Y      |  Go To Vehicle Part Consumer Panel |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|      EXIT    |  EXIT Admin Platform               |");
				System.out.println("-----------------------------------------------------");
				System.out.println("");
				
				System.out.print("Enter Command : ");
				String input = br.readLine().toUpperCase();
				System.out.println("");
				
				switch(input) {
				case "Q" : 
					ArrayList<Part> partList = new ArrayList<Part>();
					partList = vehiclePartService.getPartsAll();
					if (partList.size() > 0) {
						System.out.println("-----------------------------------------");
						System.out.println("Vehicle Parts Registered In The System");
						System.out.println("-----------------------------------------");
						System.out.println("");
						
						for(Part onePart : partList) {
							System.out.println("-----------------------------------------");
							System.out.println("ID : " + onePart.getId());
							System.out.println("Name : " + onePart.getName());
							System.out.println("Brand : " + onePart.getBrand());
							System.out.println("Model : " + onePart.getModel());
							System.out.println("Price : Rs." + decfor.format(onePart.getPrice()));
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
					}else {
						System.out.println("-----------------------------------------");
						System.out.println("No Vehicle Parts Registered In The System");
						System.out.println("-----------------------------------------");
					}
					break;
					
				case "W" : 
					Part part = new Part();
					System.out.print("Enter The Required Vehicle Part ID : ");
					String id = br.readLine();
					part = vehiclePartService.getOnePart(Integer.parseInt(id));
					System.out.println("");
					
					if(part == null) {
						System.out.println("-----------------------------------------");
						System.out.println("No Vehicle Parts Registered In The System For ID : " + id);
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					else {
						System.out.println("-----------------------------------------");
						System.out.println("ID : " + part.getId());
						System.out.println("Name : " + part.getName());
						System.out.println("Brand : " + part.getBrand());
						System.out.println("Model : " + part.getModel());
						System.out.println("Price : Rs." + decfor.format(part.getPrice()));
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					break;
					
				case "E" : 
					System.out.println("--------Enter Details Of The New Vehicle Part -------");
					String name = "";
					while(name.length() == 0) {
						System.out.print("Enter the Vehicle Part Name : ");
						name = br.readLine();
						if(name.length() == 0) {
							System.out.println("Please Enter a Name!!!");
							System.out.println("");
						}
					}
					
					String brand = "";
					while(brand.length() == 0) {
						System.out.print("Enter the Vehicle Part Brand : ");
						brand = br.readLine();
						if(brand.length() == 0) {
							System.out.println("Please Enter a Brand!!!");
							System.out.println("");
						}
					}
					
					String model = "";
					while(model.length() == 0) {
						System.out.print("Enter the Vehicle Part Model : ");
						model = br.readLine();
						if(model.length() == 0) {
							System.out.println("Please Enter a Model!!!");
							System.out.println("");
						}
					}
					
					String priceInput = "";
					double price = 0;
					while(priceInput.length() == 0) {
						System.out.print("Enter the Vehicle Price(In Rupees): ");
						priceInput = br.readLine();
						if(priceInput.length() == 0) {
							System.out.println("Please Enter a Value!!!");
							System.out.println("");
						}else {
							price = Double.parseDouble(priceInput);
						}
					}
					
					vehiclePartService.insertPart(name, brand, model, price);
					System.out.println("");
					System.out.println("-----------------------------------------");
					System.out.println("|         New Vehicle Part Added         |");
					System.out.println("-----------------------------------------");
					System.out.println("");
					break;
					
				case "R" : 
					System.out.println("--------Update Details Of The Vehicle Part-------");
					String inputID = "";
					part = new Part();
					while(inputID.length() == 0) {
						System.out.print("Enter The ID Of The Vehicle Part That Need To Update : ");
						inputID = br.readLine();
						if(inputID.length() == 0) {
							System.out.println("Please Enter a Value!!!");
							System.out.println("");
						}
						else {
							part = vehiclePartService.getOnePart(Integer.parseInt(inputID));
						}
					}
					
					System.out.println("");
					
					if(part == null) {
						System.out.println("-----------------------------------------");
						System.out.println("No Vehicle Parts Registered In The System For ID : " + inputID);
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					else {
						System.out.println("-------------------------------");
						System.out.println("ID : " + part.getId());
						System.out.println("Name : " + part.getName());
						System.out.println("Brand : " + part.getBrand());
						System.out.println("Model : " + part.getModel());
						System.out.println("Price : Rs." + decfor.format(part.getPrice()));
						System.out.println("-----------------------------------------");
						System.out.println("");
						
						name = "";
						brand = "";
						model = "";
						priceInput = "";						
						
						System.out.println("-------=Keep a BLANK to skip updating an element------");
						
						System.out.print("Enter the Vehicle Part Name : ");
						name = br.readLine();
						if(name.length() == 0) {
							name = part.getName();
						}
						
						System.out.print("Enter the Vehicle Part Brand : ");
						brand = br.readLine();
						if(brand.length() == 0) {
							brand = part.getModel();
						}
						
						System.out.print("Enter the Vehicle Part Model : ");
						model = br.readLine();
						if(model.length() == 0) {
							model = part.getModel();
						}
						
						System.out.print("Enter the Vehicle Price(In Rupees): ");
						priceInput = br.readLine();
						if(priceInput.length() == 0) {
							price = part.getPrice();
						}
						else {
							price = Double.parseDouble(priceInput);
						}
						
						vehiclePartService.updatePart(Integer.parseInt(inputID), name, brand, model, price);
						System.out.println("");
						System.out.println("-----------------------------------------");
						System.out.println("|      Vehicle Part Details Updated      |");
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					break;
					
				case "T" : 
					System.out.println("--------Update Details Of The Vehicle Part-------");
					inputID = "";
					part = new Part();
					while(inputID.length() == 0) {
						System.out.print("Enter The ID Of The Vehicle Part That Need To Delete : ");
						inputID = br.readLine();
						if(inputID.length() == 0) {
							System.out.println("Please Enter a Value!!!");
							System.out.println("");
						}
						else {
							part = vehiclePartService.getOnePart(Integer.parseInt(inputID));
						}
					}
					
					System.out.println("");
					
					if(part == null) {
						System.out.println("-----------------------------------------");
						System.out.println("No Vehicle Parts Registered In The System For ID : " + inputID);
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					else {
						System.out.println("-------------------------------");
						System.out.println("ID : " + part.getId());
						System.out.println("Name : " + part.getName());
						System.out.println("Brand : " + part.getBrand());
						System.out.println("Model : " + part.getModel());
						System.out.println("Price : Rs." + decfor.format(part.getPrice()));
						System.out.println("-----------------------------------------");
						System.out.println("");
						
						System.out.print("Do You Want To Delete This Vehicle Item (Y | N): ");
						String delete = br.readLine();
						if(delete.equalsIgnoreCase("Y")) {
							vehiclePartService.deletePart(Integer.parseInt(inputID));
							System.out.println("");
							System.out.println("-----------------------------------------");
							System.out.println("|      Vehicle Part Details Deleted     |");
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						else {
							System.out.println("");
						}
					}
					
					break;
					
				case "Y" : 
					ConsumerActivator consumerActivator = new ConsumerActivator();
					consumerActivator.start(bundleContext);
					break;
					
				case "EXIT" : 
					exit = true;
					break;
					
				default:
					System.out.println("Invalid Input!! Please Enter A Command From The Givestarn List!!");
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
