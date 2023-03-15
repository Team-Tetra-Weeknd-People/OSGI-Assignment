package sa_osgi_toysservice_consumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import sa_osgi_toysservice.*;


public class ConsumerActivator implements BundleActivator {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final DecimalFormat decfor = new DecimalFormat("0.00");
	
	ServiceReference serviceReference;
	
	String type;
	String ageGroup;
	boolean status;
	boolean allEmpty;
	String exit;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("");
		serviceReference = context.getServiceReference(ToysService.class.getName());
		ToysService toysService = (ToysService)context.getService(serviceReference);
		
		ArrayList<ToysModel> toysList = new ArrayList<ToysModel>();
		ArrayList<ToysModel> toys = new ArrayList<ToysModel>();
		
		toysList = toysService.getAlltoys();
		
		System.out.println("----------------------- Welcome to the TOYS Shop -----------------------");
		
		
		try {
			while(true) {
				
				type = "";
				ageGroup = "";
				status = false;
				allEmpty = false;
				toys.removeAll(toys);

				System.out.print("Enter the type of the toy (Teddy Bear/ Remote Car/ Doll / GamePad) : ");
				type = br.readLine();
				
				System.out.print("Enter the desired Age Group (toddler/ primary/ secondary) : ");
				ageGroup = br.readLine();
				

				if (type.length() != 0 && ageGroup.length() != 0) {
					status = toysService.checkToysAvailability(type, ageGroup);
				}

				else if (type.length() == 0 && ageGroup.length() != 0) {
					status = toysService.checkToysByAgeGroup(ageGroup);
				}
				else if(type.length() != 0 && ageGroup.length() == 0) {
					status = toysService.checkToysByType(type);
				}

				else {
					allEmpty = true;
				}
				
				if(status) {
					toys = toysService.gettoys();
					System.out.println("\n-------------------------------------");
					for(ToysModel oneToy : toys) {
						System.out.println("Name - " + oneToy.getType());
						System.out.println("Brand - " + oneToy.getAgeGroup());
						System.out.println("Price - Rs." + decfor.format(oneToy.getPrice()));
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
						this.stop(context);
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
		
		

	public void stop(BundleContext context) throws Exception {
		context.ungetService(serviceReference);
	}

}
