package sa_osgi_clothingconsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import sa_osgi_clothingservice.ClothingService;


public class ConsumerActivator implements BundleActivator {

	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  
	
	public void start(BundleContext bundleContext) throws Exception {
		serviceRef = bundleContext.getServiceReference(ClothingService.class.getName());
		ClothingService clothingService = (ClothingService)bundleContext.getService(serviceRef);
		
		try {
			System.out.println("Enter the brand name, type and size to check availability and the price.");
			System.out.println("Enter Blank to exit.");
			
			 String brandName = "";
			 String type = "";
			 String size = "";
	         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	         
	         while (true) {
				System.out.print("Enter the brand name(Nike|Polo|Adidas|Moose)  : ");
				brandName = in.readLine();
				System.out.print("Enter the type(Trouser|T-Shirt|Shirt)         : ");
				type = in.readLine();
				System.out.print("Enter the size(S|M|L|Waist Size(If a trouser)): ");
				size = in.readLine();
				System.out.println("");
				
				if(brandName.length()==0||type.length()==0||size.length()==0) {
					System.out.println("Exiting...");
					break;
				}
				else if(clothingService == null) {
					System.out.println("Not Connected to the Clothing Store.");
					break;
				}
				else if (clothingService.checkClothingAvailability(brandName, type, size)) {
					System.out.println("-----------------------------------------------");
					System.out.println("Clothing Item Available.Details are as Follows.");
					System.out.println("-----------------------------------------------");
					System.out.println("Brand : "+ brandName.toUpperCase());
					System.out.println("Type  : "+ type.toUpperCase());
					System.out.println("Size  : "+ size.toUpperCase());
					System.out.println("Price : Rs."+ decfor.format(clothingService.price()));
					System.out.println("-----------------------------------------------");
					System.out.println("---------   Thank You For Browsing   ----------");
					System.out.println("");
				}
				else {
					System.out.println("Item you are looking for is Unavailable.");
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
