package com.miti.vehiclepart.comsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.vehiclepart.service.*;

public class ConsumerActivator implements BundleActivator {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	String name;
	String brand;
	String model;
	boolean status;
	boolean allEmpty;
	String exit;
	
	
	public void start(BundleContext bundleContext) throws Exception {
		serviceRef = bundleContext.getServiceReference(VehiclePartsService.class.getName());
		VehiclePartsService vehiclePartService = (VehiclePartsService)bundleContext.getService(serviceRef);
		
		
		ArrayList<Part> partList = new ArrayList<Part>();
		ArrayList<Part> parts = new ArrayList<Part>();
		
		partList = vehiclePartService.getAllParts();
		
		System.out.print("---------------------");
		System.out.print("Welcome to the Vehicle Parts Section");
		System.out.println("---------------------\n");
		
		try {
			while(true) {
				
				name = "";
				brand = "";
				model = "";
				status = false;
				allEmpty = false;
				parts.removeAll(parts);

				System.out.print("Enter the Part Name (Front-Bumber | Rear-Bumper ...etc) : ");
				name = br.readLine();
				
				System.out.print("Enter the Vehicle Brand (Toyota | Nissan ...etc) : ");
				brand = br.readLine();
				
				System.out.print("Enter the Vehicle Model (Premio | Leaf ...etc) : ");
				model = br.readLine();
				
				if (name.length() != 0 && brand.length() != 0 && model.length() != 0) {
					status = vehiclePartService.checkPartAvailability(name, brand, model);
				}
				else if(name.length() != 0 && brand.length() != 0 && model.length() == 0) {
					status = vehiclePartService.checkPartByNameNBrand(name, brand);
				}
				else if (name.length() == 0 && brand.length() != 0 && model.length() != 0) {
					status = vehiclePartService.checkPartByBrandNModel(brand, model);
				}
				else if (name.length() != 0 && brand.length() == 0 && model.length() != 0) {
					status = vehiclePartService.checkPartByNameNModel(name, model);
				}
				else if (name.length() == 0 && brand.length() != 0 && model.length() == 0) {
					status = vehiclePartService.checkPartByBrand(brand);
				}
				else if(name.length() != 0 && brand.length() == 0 && model.length() == 0) {
					status = vehiclePartService.checkPartByName(name);
				}
				else if(name.length() == 0 && brand.length() == 0 && model.length() != 0){
					status = vehiclePartService.checkPartByModel(model);
				}
				else {
					allEmpty = true;
				}
				
				if(status) {
					parts = vehiclePartService.getParts();
					System.out.println("\n-------------------------------------");
					for(Part onePart : parts) {
						System.out.println("Name - " + onePart.getName());
						System.out.println("Brand - " + onePart.getBrand());
						System.out.println("Model - " + onePart.getModel());
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
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good-Bye");
		bundleContext.ungetService(serviceRef);
	}

}
