package sa_osgi_toysservice_admin_it21008382;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sa_osgi_toysservice.*;
import sa_osgi_toysservice_consumer.ConsumerActivator;

public class AdminActivator implements BundleActivator {

	ServiceReference serviceReference;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	@SuppressWarnings("null")
	public void start(BundleContext context) throws Exception {
		
		serviceReference = context.getServiceReference(ToysService.class.getName());
		@SuppressWarnings("unchecked")
		ToysService toysService = (ToysService)context.getService(serviceReference);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;
		
		try {
			System.out.println("----------------------------------------------------");
			System.out.println(" Welcome to TOYS Shop Administrative Panel");
			System.out.println("----------------------------------------------------");
			System.out.println("");
			
			while(true) {
				System.out.println(" Use the below given COMMANDS to use the access the functions");
				System.out.println("");
				System.out.println("-----------------------------------------------------");
				System.out.println("|     Command  |             Action                 |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       A      |  View All Toys                     |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       P      |  View one Toys by ID               |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       I      |  Insert a Toy                      |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       U      |  Update a Toy by ID                |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       D      |  Delete a Toy by ID                |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|       T      |  Go To Toys Shop                   |");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|      EXIT    |  EXIT Admin Platform               |");
				System.out.println("-----------------------------------------------------");
				System.out.println("");
				
				System.out.print("Enter Command : ");
				String input = br.readLine().toUpperCase();
				System.out.println("");
				
				switch(input) {
				case "A" : 
					ArrayList<ToysModel> toysList = new ArrayList<ToysModel>();
					toysList = toysService.getToysAll();
					if (toysList.size() > 0) {
						System.out.println("-----------------------------------------");
						System.out.println("All toys that are already in the System");
						System.out.println("-----------------------------------------");
						System.out.println("");
						
						for(ToysModel oneToy : toysList) {
							System.out.println("-----------------------------------------");
							System.out.println("ID : " + oneToy.getId());
							System.out.println("Type : " + oneToy.getType());
							System.out.println("Age Group : " + oneToy.getAgeGroup());
							System.out.println("Price : Rs." + decfor.format(oneToy.getPrice()));
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
					}else {
						System.out.println("-----------------------------------------");
						System.out.println("No Toys are In The System");
						System.out.println("-----------------------------------------");
					}
					break;
					
				case "P" : 
					ToysModel toy = new ToysModel();
					System.out.print("Enter The Required Toy ID : ");
					String id = br.readLine();
					toy = toysService.getOneToy(Integer.parseInt(id));
					System.out.println("");
					
					if(toy == null) {
						System.out.println("-----------------------------------------");
						System.out.println("No toy In The System For ID : " + id);
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					else {
						System.out.println("-----------------------------------------");
						System.out.println("ID : " + toy.getId());
						System.out.println("Type : " + toy.getType());
						System.out.println("Age Group : " + toy.getAgeGroup());
						System.out.println("Price : Rs." + decfor.format(toy.getPrice()));
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					break;
					
				case "I" : 
					System.out.println("--------Enter Details Of The New Toy -------");
					String type = "";
					while(type.length() == 0) {
						System.out.print("Enter the type of the Toy (Teddy Bear / Remote Car / Doll / GamePad) : ");
						type = br.readLine();
						if(type.length() == 0) {
							System.out.println("Please Enter a type!!!");
							System.out.println("");
						}
					}
					
					String ageGroup = "";
					while(ageGroup.length() == 0) {
						System.out.print("Enter the age Group for the Toy (Toddler / Primary / Secondary): ");
						ageGroup = br.readLine();
						if(ageGroup.length() == 0) {
							System.out.println("Please Enter a age Group!!!");
							System.out.println("");
						}
					}
					
					
					String priceInput = "";
					double price = 0;
					while(priceInput.length() == 0) {
						System.out.print("Enter the Toy Price(In Rupees): ");
						priceInput = br.readLine();
						if(priceInput.length() == 0) {
							System.out.println("Please Enter a Value!!!");
							System.out.println("");
						}else {
							price = Double.parseDouble(priceInput);
						}
					}
					
					toysService.insertToy(type, ageGroup, price);
					System.out.println("");
					System.out.println("-----------------------------------------");
					System.out.println("|         New Toy Added         |");
					System.out.println("-----------------------------------------");
					System.out.println("");
					break;
					
				case "U" : 
					System.out.println("-------- Update Details Of a Toy -------");
					String inputID = "";
					toy = new ToysModel();
					while(inputID.length() == 0) {
						System.out.print("Enter The ID Of The Toy That Need To Update : ");
						inputID = br.readLine();
						if(inputID.length() == 0) {
							System.out.println("Please Enter a Value!!!");
							System.out.println("");
						}
						else {
							toy = toysService.getOneToy(Integer.parseInt(inputID));
						}
					}
					
					System.out.println("");
					
					if(toy == null) {
						System.out.println("-----------------------------------------");
						System.out.println("No Toys In The System For ID : " + inputID);
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					else {
						System.out.println("-------------------------------");
						System.out.println("ID : " + toy.getId());
						System.out.println("Type : " + toy.getType());
						System.out.println("Age Group : " + toy.getAgeGroup());
						System.out.println("Price : Rs." + decfor.format(toy.getPrice()));
						System.out.println("-----------------------------------------");
						System.out.println("");
						
						type = "";
						ageGroup = "";
						priceInput = "";						
						
						System.out.println("-------=Keep a BLANK to skip updating an element------");
						
						System.out.print("Enter the Type of the Toy (Teddy Bear / Remote Car / Doll / GamePad): ");
						type = br.readLine();
						if(type.length() == 0) {
							type = toy.getType();
						}
						
						System.out.print("Enter the Age Group for the Toy (Toddler / Primary / Secondary) : ");
						ageGroup = br.readLine();
						if(ageGroup.length() == 0) {
							ageGroup = toy.getAgeGroup();
						}

						System.out.print("Enter the Toy Price(In Rupees): ");
						priceInput = br.readLine();
						if(priceInput.length() == 0) {
							price = toy.getPrice();
						}
						else {
							price = Double.parseDouble(priceInput);
						}
						
						toysService.updateToy(Integer.parseInt(inputID), type, ageGroup, price);
						System.out.println("");
						System.out.println("-----------------------------------------");
						System.out.println("|      Toy Details Updated      |");
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					break;
					
				case "D" : 
					System.out.println("--------Delete a Toy-------");
					inputID = "";
					toy = new ToysModel();
					while(inputID.length() == 0) {
						System.out.print("Enter The ID Of The Toy That Need To Delete : ");
						inputID = br.readLine();
						if(inputID.length() == 0) {
							System.out.println("Please Enter a Value!!!");
							System.out.println("");
						}
						else {
							toy = toysService.getOneToy(Integer.parseInt(inputID));
						}
					}
					
					System.out.println("");
					
					if(toy == null) {
						System.out.println("-----------------------------------------");
						System.out.println("No Toys In The System For ID : " + inputID);
						System.out.println("-----------------------------------------");
						System.out.println("");
					}
					else {
						System.out.println("-------------------------------");
						System.out.println("ID : " + toy.getId());
						System.out.println("Type : " + toy.getType());
						System.out.println("Age Group : " + toy.getAgeGroup());
						System.out.println("Price : Rs." + decfor.format(toy.getPrice()));
						System.out.println("-----------------------------------------");
						System.out.println("");
						
						System.out.print("Do You Want To Delete This Toy (Y | N): ");
						String delete = br.readLine();
						if(delete.equalsIgnoreCase("Y")) {
							toysService.deleteToy(Integer.parseInt(inputID));
							System.out.println("");
							System.out.println("-----------------------------------------");
							System.out.println("|      Toy successfully Deleted         |");
							System.out.println("-----------------------------------------");
							System.out.println("");
						}
						else {
							System.out.println("");
						}
					}
					
					break;
					
				case "T" : 
					ConsumerActivator consumerActivator = new ConsumerActivator();
					consumerActivator.start(context);
					break;
					
				case "EXIT" : 
					exit = true;
					break;
					
				default:
					System.out.println("Invalid Input!! Please Enter A Command From The Given List!!");
					System.out.println("");
					break;
					
				}
				
				if(exit) {
					this.stop(context);
					break;
				}
				
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		bundleContext.ungetService(serviceReference);
		System.out.println("Admin Shutting Down...");
	}
}
