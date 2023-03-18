package sa_osgi_clothingconsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import sa_osgi_clothingservice.Clothing;
import sa_osgi_clothingservice.ClothingService;


public class ConsumerActivator implements BundleActivator {

	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  
	
	ArrayList<Clothing> clothes = new ArrayList<>();
	int bCount = 0;
	
	public void start(BundleContext bundleContext) throws Exception {
		serviceRef = bundleContext.getServiceReference(ClothingService.class.getName());
		ClothingService clothingService = (ClothingService)bundleContext.getService(serviceRef);
		
		try {
			System.out.println("-------------------Enter 'END' to one input Field to EXIT--------------------");
			System.out.println("---Enter the brand name, type and size to check availability and the price---");
			System.out.println("---Enter blank to brand name, type and size to get all details of the items--");
			System.out.println("---    If you want to check the availability of clothes using only the    \n    brandname,then keep blank for other fields.ViseVersa to type and size ---");
			System.out.println("-------------------Enter 'END' to one input Field to EXIT--------------------\n");
			
			 String brandName = "";
			 String type = "";
			 String size = "";
	         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	         ArrayList<Clothing> allClothes = clothingService.getAllClothes();
	         
	         while (true) {
				System.out.print("Enter the brand name(Nike|Polo|Adidas|Moose)  : ");
				brandName = in.readLine();
				if(brandName.equalsIgnoreCase("end")) {
					System.out.println("Exiting...");
					break;
				}
				while(bCount==0) {
				for(int i = 0;i<allClothes.size();i++) {
					if(allClothes.get(i)!=null) {
					if(brandName.equalsIgnoreCase(allClothes.get(i).getBrand())||brandName.length() == 0) {
						bCount++;
					}
					}
				}
				if(bCount==0) {
					System.out.println("-------------- Invalid Brand Name --------------");
					System.out.print("Enter the brand name(Nike|Polo|Adidas|Moose)  : ");
					brandName = in.readLine();
				}
				}
				bCount = 0;
				System.out.print("Enter the type(Trouser|T-Shirt|Shirt)         : ");
				type = in.readLine();
				if(type.equalsIgnoreCase("end")) {
					System.out.println("Exiting...");
					break;
				}
				while(bCount==0) {
					for(int i = 0;i<allClothes.size();i++) {
						if(allClothes.get(i)!=null) {
						if(type.equalsIgnoreCase(allClothes.get(i).getType())||type.length() == 0) {
							bCount++;
						}
						}
					}
					if(bCount==0) {
						System.out.println("----------------- Invalid Type -----------------");
						System.out.print("Enter the type(Trouser|T-Shirt|Shirt)         : ");
						type = in.readLine();
					}
					}
					bCount = 0;
				System.out.print("Enter the size(S|M|L|Waist Size(If a trouser)): ");
				size = in.readLine();
				if(size.equalsIgnoreCase("end")) {
					System.out.println("Exiting...");
					break;
				}
				while(bCount==0) {
					for(int i = 0;i<allClothes.size();i++) {
						if(allClothes.get(i)!=null) {
						if(size.equalsIgnoreCase(allClothes.get(i).getSize())||size.length() == 0) {
							bCount++;
						}
						}
					}
					if(bCount==0) {
						System.out.println("----------------- Invalid Size -----------------");
						System.out.print("Enter the size(S|M|L|Waist Size(If a trouser)): ");
						size = in.readLine();
					}
					}
					bCount = 0;
				System.out.println("");
				
				if(brandName.length()==0&&type.length()==0&&size.length()==0) {
					clothes = clothingService.getAllClothes();
					for(int i=0; i < clothes.size();i++) {	
						System.out.println("--------------------------------------------------");
						System.out.println("ID    : "+ clothes.get(i).getId());
						System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
						System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
						System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
						System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
						System.out.println("--------------------------------------------------");
						System.out.println("");
					}
					System.out.println("------------   Thank You For Browsing  -----------");
					System.out.println("");
				}
				else if(clothingService == null) {
					System.out.println("Not Connected to the Clothing Store.");
					break;
				}
				else if (clothingService.checkClothingAvailability(brandName, type, size)) {
					clothes = clothingService.results();
					System.out.println("--------------------------------------------------");
					System.out.println("Clothing Item(s) Available.Details are as Follows.");
					System.out.println("--------------------------------------------------");
					for(int i=0; i < clothingService.getCount();i++) {		
						System.out.println("ID    : "+ clothes.get(i).getId());
						System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
						System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
						System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
						System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
						System.out.println("--------------------------------------------------");
						System.out.println("");
					}
					System.out.println("------------   Thank You For Browsing  -----------");
					System.out.println("");
				}
				else if(brandName.length()!=0 && type.length() != 0) {
					if(clothingService.checkClothingAvailabilityBT(brandName,type)) {
						clothes = clothingService.results();
						System.out.println("--------------------------------------------------");
						System.out.println("Clothing Item(s) Available.Details are as Follows.");
						System.out.println("--------------------------------------------------");
						for(int i=0; i < clothingService.getCount();i++) {		
							System.out.println("ID    : "+ clothes.get(i).getId());
							System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
							System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
							System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
							System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
							System.out.println("--------------------------------------------------");
							System.out.println("");
						}
						System.out.println("------------   Thank You For Browsing  -----------");
						System.out.println("");
					}else {
						System.out.println("Item(s) you are looking for is/are Unavailable.");
						System.out.println("");
					}
				}
				else if(brandName.length()!=0 && size.length() != 0) {
					if(clothingService.checkClothingAvailabilityBS(brandName,size)) {
						clothes = clothingService.results();
						System.out.println("--------------------------------------------------");
						System.out.println("Clothing Item(s) Available.Details are as Follows.");
						System.out.println("--------------------------------------------------");
						for(int i=0; i < clothingService.getCount();i++) {		
							System.out.println("ID    : "+ clothes.get(i).getId());
							System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
							System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
							System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
							System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
							System.out.println("--------------------------------------------------");
							System.out.println("");
						}
						System.out.println("------------   Thank You For Browsing  -----------");
						System.out.println("");
					}else {
						System.out.println("Item(s) you are looking for is/are Unavailable.");
						System.out.println("");
					}
				}
				else if(type.length()!=0 && size.length() != 0) {
					if(clothingService.checkClothingAvailabilityTS(type,size)) {
						clothes = clothingService.results();
						System.out.println("--------------------------------------------------");
						System.out.println("Clothing Item(s) Available.Details are as Follows.");
						System.out.println("--------------------------------------------------");
						for(int i=0; i < clothingService.getCount();i++) {		
							System.out.println("ID    : "+ clothes.get(i).getId());
							System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
							System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
							System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
							System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
							System.out.println("--------------------------------------------------");
							System.out.println("");
						}
						System.out.println("------------   Thank You For Browsing  -----------");
						System.out.println("");
					}else {
						System.out.println("Item(s) you are looking for is/are Unavailable.");
						System.out.println("");
					}
				}
				else if (brandName.length()!=0) {
					if(clothingService.checkClothingAvailabilityBrand(brandName)) {
						clothes = clothingService.results();
						System.out.println("--------------------------------------------------");
						System.out.println("Clothing Item(s) Available.Details are as Follows.");
						System.out.println("--------------------------------------------------");
						for(int i=0; i < clothingService.getCount();i++) {		
							System.out.println("ID    : "+ clothes.get(i).getId());
							System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
							System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
							System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
							System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
							System.out.println("--------------------------------------------------");
							System.out.println("");
						}
						System.out.println("------------   Thank You For Browsing  -----------");
						System.out.println("");
					}else {
						System.out.println("Item(s) you are looking for is/are Unavailable.");
						System.out.println("");
					}
				}
				else if (type.length()!=0) {
					if(clothingService.checkClothingAvailabilityType(type)) {
						clothes = clothingService.results();
						System.out.println("--------------------------------------------------");
						System.out.println("Clothing Item(s) Available.Details are as Follows.");
						System.out.println("--------------------------------------------------");
						for(int i=0; i < clothingService.getCount();i++) {		
							System.out.println("ID    : "+ clothes.get(i).getId());
							System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
							System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
							System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
							System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
							System.out.println("--------------------------------------------------");
							System.out.println("");
						}
						System.out.println("------------   Thank You For Browsing  -----------");
						System.out.println("");
					}else {
						System.out.println("Item(s) you are looking for is/are Unavailable.");
						System.out.println("");
					}
				}
				else if (size.length()!=0) {
					if(clothingService.checkClothingAvailabilitySize(size)) {
						clothes = clothingService.results();
						System.out.println("--------------------------------------------------");
						System.out.println("Clothing Item(s) Available.Details are as Follows.");
						System.out.println("--------------------------------------------------");
						for(int i=0; i < clothingService.getCount();i++) {		
							System.out.println("ID    : "+ clothes.get(i).getId());
							System.out.println("Brand : "+ clothes.get(i).getBrand().toUpperCase());
							System.out.println("Type  : "+ clothes.get(i).getType().toUpperCase());
							System.out.println("Size  : "+ clothes.get(i).getSize().toUpperCase());
							System.out.println("Price : Rs."+ decfor.format(clothes.get(i).getPrice()));
							System.out.println("--------------------------------------------------");
							System.out.println("");
						}
						System.out.println("------------   Thank You For Browsing  -----------");
						System.out.println("");
					}else {
						System.out.println("Item(s) you are looking for is/are Unavailable.");
						System.out.println("");
					}
				}
				else {
					System.out.println("Item you are looking for is/are Unavailable.");
					System.out.println("");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(serviceRef);
	}

}
