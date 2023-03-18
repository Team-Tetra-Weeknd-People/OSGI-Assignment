package sa_osgi_clothingadmin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sa_osgi_clothingconsumer.ConsumerActivator;
import sa_osgi_clothingservice.Clothing;
import sa_osgi_clothingservice.ClothingService;

public class AdminActivator implements BundleActivator {

	ServiceReference serviceRef1,serviceRef2;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	public void start(BundleContext bundleContext) throws Exception {
		serviceRef1 = bundleContext.getServiceReference(ClothingService.class.getName());
		ClothingService clothingService = (ClothingService)bundleContext.getService(serviceRef1);
		
		try {
			
			
			System.out.println(" Welcome to Clothing Store Administrative Platform");
			System.out.println("----------------------------------------------------");
			System.out.println(" Use the below given COMMANDS to use the platform");
			System.out.println("----------------------------------------------------");
			System.out.println("|     Command  |             Action                |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|       A      |  View All Clothing-Items          |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|       B      |  View Clothing-Items by refernces |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|       C      |  Insert a Clothing-Item           |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|       R      |  View a Clothing-Item by ID       |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|       U      |  Update a Clothing-Item by ID     |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|       D      |  Delete a Clothing-Item by ID     |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|      END     |  EXIT Admin Platform              |");
			System.out.println("----------------------------------------------------");
			System.out.println("");
			
			ArrayList<Clothing> clothings = new ArrayList<Clothing>();
			
			while (true) {
				String brandName = "";
				String type = "";
				String size = "";
				String tempString = "";
				int id = 0;
				double price = 0;
				String inputCommand ;
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter Command :");
				inputCommand = in.readLine();
				
				clothings = clothingService.getAllClothes();
				switch (inputCommand.toLowerCase()) {
				case "a":
					for(int i=0; i < clothings.size();i++) {	
						System.out.println("--------------------------------------------------");
						System.out.println("ID    : "+ clothings.get(i).getId());
						System.out.println("Brand : "+ clothings.get(i).getBrand().toUpperCase());
						System.out.println("Type  : "+ clothings.get(i).getType().toUpperCase());
						System.out.println("Size  : "+ clothings.get(i).getSize().toUpperCase());
						System.out.println("Price : Rs."+ decfor.format(clothings.get(i).getPrice()));
						System.out.println("--------------------------------------------------");
						System.out.println("");
					}
					break;
				case "b":
					ConsumerActivator consumerActivator = new ConsumerActivator();
					consumerActivator.start(bundleContext);
					break;
				case "c":
					System.out.println("--------Enter Details of the new Clothing Item -------");
					System.out.println("--------    Enter 'END' to TERMINATE setup     -------");
					while (brandName.length()==0) {
						clothings = clothingService.getAllClothes();
						System.out.print("Enter the brand name(Nike|Polo|Adidas|Moose)  : ");
						brandName = in.readLine();
						if (brandName.equalsIgnoreCase("end")) {
							break;
						}
					}
					if (brandName.equalsIgnoreCase("end")) {
						break;
					}
					while (type.length()==0) {
						System.out.print("Enter the type(Trouser|T-Shirt|Shirt)         : ");
						type = in.readLine();
						if (type.equalsIgnoreCase("end")) {
							break;
						}
					}
					if (type.equalsIgnoreCase("end")) {
						break;
					}
					while (size.length()==0) {
						System.out.print("Enter the size(S|M|L|Waist Size(If a trouser)): ");
						size = in.readLine();
						if (size.equalsIgnoreCase("end")) {
							break;
						}
					}
					if (size.equalsIgnoreCase("end")) {
						break;
					}
					while (price==0) {
						System.out.print("Enter the Price                               : ");
						tempString = in.readLine();
						if (tempString.equalsIgnoreCase("end")) {
							break;
						}
						else if (tempString.length()==0) {
							price = 0;
						}else {
							price = Double.parseDouble(tempString);
						}
					}
					if (tempString.equalsIgnoreCase("end")) {
						break;
					}
					clothingService.insertDataToDB(brandName, type, size, price);
					break;
				case "r":
					System.out.println("-------------Enter 'END' to TERMINATE------------");
					while (true) {
						clothings = clothingService.getAllClothes();
						System.out.print("Enter ID of the Item (To View Details):");
						tempString = in.readLine();
						if (tempString.equalsIgnoreCase("end")) {
							break;
						}else if (tempString.length()!=0) {
							try {
								id = Integer.parseInt(tempString);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}	
						int c2 = 0;
						for(int i=0; i < clothings.size();i++) {	
							if(clothings.get(i).getId()==id) {
								System.out.println("--------------------------------------------------");
								System.out.println("ID    : "+ clothings.get(i).getId());
								System.out.println("Brand : "+ clothings.get(i).getBrand().toUpperCase());
								System.out.println("Type  : "+ clothings.get(i).getType().toUpperCase());
								System.out.println("Size  : "+ clothings.get(i).getSize().toUpperCase());
								System.out.println("Price : Rs."+ decfor.format(clothings.get(i).getPrice()));
								System.out.println("--------------------------------------------------");
								System.out.println("");
								c2++;
							}
						}
						if (c2==0) {
							System.out.println("Theres no item with the Entered ID!");
						}
					}
					break;
				case "u":
					System.out.println("-------------Enter 'END' to TERMINATE------------");
					while (true) {
						clothings = clothingService.getAllClothes();
						System.out.print("Enter ID of the Item (To Be Updated):");
						tempString = in.readLine();
						if (tempString.equalsIgnoreCase("end")) {
							break;
						}else if (tempString.length()!=0) {
							try {
								id = Integer.parseInt(tempString);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}	
						int c = 0;
						for(int i=0; i < clothings.size();i++) {	
							if(clothings.get(i).getId()==id) {
								System.out.println("--------------------------------------------------");
								System.out.println("ID    : "+ clothings.get(i).getId());
								System.out.println("Brand : "+ clothings.get(i).getBrand().toUpperCase());
								System.out.println("Type  : "+ clothings.get(i).getType().toUpperCase());
								System.out.println("Size  : "+ clothings.get(i).getSize().toUpperCase());
								System.out.println("Price : Rs."+ decfor.format(clothings.get(i).getPrice()));
								System.out.println("--------------------------------------------------");
								System.out.println("");
								c++;
							}
						}
						if (c==0) {
							System.out.println("Theres no item with the Entered ID!");
						}
						else {
							System.out.println("--------Enter BLANK to skip updating a detail------");
							System.out.print("Enter the brand name(Nike|Polo|Adidas|Moose)  : ");
							brandName = in.readLine();
							if (brandName.length()==0) {
								brandName = clothingService.getClothingFromID(id).getBrand();
							}
							System.out.print("Enter the type(Trouser|T-Shirt|Shirt)         : ");
							type = in.readLine();
							if (type.length()==0) {
								type = clothingService.getClothingFromID(id).getType();
							}
							System.out.print("Enter the size(S|M|L|Waist Size(If a trouser)): ");
							size = in.readLine();
							if (size.length()==0) {
								size = clothingService.getClothingFromID(id).getSize();
							}
							System.out.print("Enter the Price                               : ");
							tempString = in.readLine();
							if (tempString.length()==0) {
								price = 0;
							}else {
								price = Double.parseDouble(tempString);
							}
							if (price==0) {
								price = clothingService.getClothingFromID(id).getPrice();
							}
							clothingService.updateDB(id, brandName, type, size, price);
							}
					}
					
					break;
				case "d":
					System.out.println("-------------Enter 'END' to TERMINATE------------");
					while (true) {
						clothings = clothingService.getAllClothes();
						System.out.print("Enter ID of the Item (To Delete Details):");
						tempString = in.readLine();
						if (tempString.equalsIgnoreCase("end")) {
							break;
						}else if (tempString.length()!=0) {
							try {
								id = Integer.parseInt(tempString);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}	
						int c1 = 0;
						for(int i=0; i < clothings.size();i++) {	
							if(clothings.get(i).getId()==id) {
								System.out.println("--------------------------------------------------");
								System.out.println("ID    : "+ clothings.get(i).getId());
								System.out.println("Brand : "+ clothings.get(i).getBrand().toUpperCase());
								System.out.println("Type  : "+ clothings.get(i).getType().toUpperCase());
								System.out.println("Size  : "+ clothings.get(i).getSize().toUpperCase());
								System.out.println("Price : Rs."+ decfor.format(clothings.get(i).getPrice()));
								System.out.println("--------------------------------------------------");
								System.out.println("");
								c1++;
							}
						}
						if (c1==0) {
							System.out.println("Theres no item with the Entered ID!");
						}else {
							System.out.println("Are you sure you want to delete this item(Y/N)?");
							inputCommand = in.readLine();
							if (inputCommand.equalsIgnoreCase("y")) {
								clothingService.deleteFromDB(id);
							}else {
								System.out.println("Item Deletion aborted!");
							}
						}
					}
					break;
				case "end":
					System.out.println("Exiting Admin Platform...");
					break;
				case "":
					System.out.println(" Use the below given COMMANDS to use the platform");
					System.out.println("----------------------------------------------------");
					System.out.println("|     Command  |             Action                |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       A      |  View All Clothing-Items          |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       B      |  View Clothing-Items by refernces |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       C      |  Insert a Clothing-Item           |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       R      |  View a Clothing-Item by ID       |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       U      |  Update a Clothing-Item by ID     |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       D      |  Delete a Clothing-Item by ID     |");
					System.out.println("----------------------------------------------------");
					System.out.println("|      END     |  EXIT Admin Platform              |");
					System.out.println("----------------------------------------------------");
					System.out.println("");
					break;
				default:
					System.out.println(" Use the below given COMMANDS to use the platform");
					System.out.println("----------------------------------------------------");
					System.out.println("|     Command  |             Action                |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       A      |  View All Clothing-Items          |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       B      |  View Clothing-Items by refernces |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       C      |  Insert a Clothing-Item           |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       R      |  View a Clothing-Item by ID       |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       U      |  Update a Clothing-Item by ID     |");
					System.out.println("|--------------------------------------------------|");
					System.out.println("|       D      |  Delete a Clothing-Item by ID     |");
					System.out.println("----------------------------------------------------");
					System.out.println("|      END     |  EXIT Admin Platform              |");
					System.out.println("----------------------------------------------------");
					System.out.println("");
					break;
				}
				if (inputCommand.equalsIgnoreCase("end")) {
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(serviceRef1);
	}

}
