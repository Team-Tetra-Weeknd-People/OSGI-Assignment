package sa_osgi_vehicleparts_consumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.vehiclepart.service.VehiclePart;
import com.mtit.vehiclepart.service.Part;
import com.mtit.vehiclepart.service.Paint;
import com.mtit.vehiclepart.service.Fluid;
import com.mtit.vehiclepart.service.VehiclePartsService;


public class ConsumerActivator implements BundleActivator {

	ServiceReference serviceRef;
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  
	
	Part[] parts;
	Paint[] paints;
	Fluid[] fluids;
	String name;
	String brand;
	String model;
	String colorCode;
	String volume;
	int select;
	boolean scanner;
	boolean status;
	boolean allEmpty;
	
	Scanner scan = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void start(BundleContext bundleContext) throws Exception {
		serviceRef = bundleContext.getServiceReference(VehiclePartsService.class.getName());
		VehiclePartsService vehiclePartService = (VehiclePartsService)bundleContext.getService(serviceRef);
		
		Part[] allParts = vehiclePartService.getAllParts();
		Paint[] allPaints = vehiclePartService.getAllPaints();
		Fluid[] allFluids = vehiclePartService.getAllFluids();
		
		try {
			while(true) {
	
				name = "";
				brand = "";
				model = "";
				colorCode = "";
				volume = "";
				select = 0;
				scanner = false;
				status = false;
				allEmpty = false;
				
				while (select > 4 || select < 1) {
					System.out.println("--------------------------------------");
					System.out.println("Select the item you need to browse -> ");
					System.out.println("(Enter the required number)");
					System.out.println("---------- 1 -> Vehicle Parts");
					System.out.println("---------- 2 -> Vehicle Paints");
					System.out.println("---------- 3 -> Vehicle Fluids");
					System.out.println("---------- 4 -> Exit System");
					System.out.print("-------------------------------------- ->");
					select = scan.nextInt();
					
					if(select > 4 || select < 1) {
						System.out.println("Invalid Selection!!!!");
						System.out.println("Please Try Again\n\n");
					}
				}
				
				if(select == 1) {
					//vehicle parts
					
					System.out.print("Enter the Part Name (Front-Bumber | Rear-Bumper | Headlights | Brakelights | Side-Mirrors) : ");
					name = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allParts.length ; i++) {
							if(name.equalsIgnoreCase(allParts[i].getName()) || name.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Vehicle Part Name --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Part Name (Front-Bumber | Rear-Bumper | Headlights | Brakelights | Side-Mirrors) : ");
							name = br.readLine();
						}
					}
					scanner = false;
					
					System.out.print("Enter the Vehicle Brand (Toyota | BWM | Nissan) : ");
					brand = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allParts.length ; i++) {
							if(brand.equalsIgnoreCase(allParts[i].getBrand()) || brand.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Vehicle Brand --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Vehicle Brand (Toyota | BWM | Nissan) : ");
							brand = br.readLine();
						}
					}
					scanner = false;
					
					System.out.print("Enter the Vehicle Model (Premio | Aqua | 318i | 530e | Leaf ) : ");
					model = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allParts.length ; i++) {
							if(model.equalsIgnoreCase(allParts[i].getModel()) || model.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Vehicle Model --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Vehicle Model (Premio | Aqua | 318i | 530e | Leaf) : ");
							model = br.readLine();
						}
					}
					scanner = false;

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
						
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("Following vehicle part(s) is/are available!!!");
						System.out.println("-------------------------------------");
						
						for(int i = 0 ; i < vehiclePartService.getCount() ; i++) {
							System.out.println("Name - " + parts[i].getName());
							System.out.println("Brand - " + parts[i].getBrand());
							System.out.println("Model - " + parts[i].getModel());
							System.out.println("Price - Rs." + decfor.format(parts[i].getPrice()));
							System.out.println("-------------------------------------");
						}
					}
					else if (allEmpty){
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("No Items has been selected");
						System.out.println("-------------------------------------");
					}
					else{
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("Item(s) you are looking for is/are Currently not available.");
						System.out.println("-------------------------------------");
					}
					
					System.out.println("");
					select = 0;
				}
				else if(select == 2) {
					//vehicle paint
					System.out.print("Enter the Color Name (White | Black | Blue) : ");
					name = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allPaints.length ; i++) {
							if(name.equalsIgnoreCase(allPaints[i].getName()) || name.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Color Name --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Color Name (White | Black | Blue) : ");
							name = br.readLine();
						}
					}
					scanner = false;
					
					System.out.print("Enter the Color Code (1 | 2 | 3) : ");
					colorCode = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allPaints.length ; i++) {
							if(colorCode.equalsIgnoreCase(allPaints[i].getColorCode()) || colorCode.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Color Name --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Color Code (1 | 2 | 3) : ");
							colorCode = br.readLine();
						}
					}
					scanner = false;
					
					System.out.print("Enter the Volume (2L | 4L) : ");
					volume = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allPaints.length ; i++) {
							if(volume.equalsIgnoreCase(allPaints[i].getVolume()) || volume.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Color Name --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Volume (2L | 4L) : ");
							volume = br.readLine();
						}
					}
					scanner = false;
					
					if (name.length() != 0 && colorCode.length() != 0 && volume.length() != 0) {
						status = vehiclePartService.checkPaintAvailability(name, colorCode, volume);
					}
					else if(name.length() == 0 && colorCode.length() != 0 && volume.length() != 0) {
						status = vehiclePartService.checkPaintByColorCodeNVolume(colorCode, volume);
					}
					else if(name.length() != 0 && colorCode.length() == 0 && volume.length() != 0) {
						status = vehiclePartService.checkPaintByColorNVolume(name, volume);
					}
					else if(name.length() != 0 && colorCode.length() != 0 && volume.length() == 0) {
						status = vehiclePartService.checkPaintByColorNColorCode(name, colorCode);
					}
					else if (name.length() != 0 && colorCode.length() == 0 && volume.length() == 0) {
						status = vehiclePartService.checkPaintByColor(name);
					}
					else if (name.length() == 0 && colorCode.length() != 0 && volume.length() == 0) {
						status = vehiclePartService.checkPaintByColorCode(colorCode);
					}
					else if (name.length() == 0 && colorCode.length() == 0 && volume.length() != 0) {
						status = vehiclePartService.checkPaintByVolume(volume);
					}
					else {
						allEmpty = true;
					}
					
					if(status) {
						paints = vehiclePartService.getPaints();
						
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("Following paint(s) is/are available!!!");
						System.out.println("-------------------------------------");
						
						for(int i = 0 ; i < vehiclePartService.getCount() ; i++) {
							System.out.println("Color Name - " + paints[i].getName());
							System.out.println("Color Code - " + paints[i].getColorCode());
							System.out.println("Volume - " + paints[i].getVolume());
							System.out.println("Price - Rs." + decfor.format(paints[i].getPrice()));
							System.out.println("-------------------------------------");
						}
					}
					else if (allEmpty){
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("No Items has been selected");
						System.out.println("-------------------------------------");
					}
					else{
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("Item(s) you are looking for is/are Currently not available.");
						System.out.println("-------------------------------------");
					}
					
					
					System.out.println("");
					select = 0;
				}
				else if (select == 3){
					//vehicle fluid
					System.out.print("Enter the Fluid Name (Brake-Oil | Engine-Oil | Transmission-Oil | Coolant) : ");
					name = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allFluids.length ; i++) {
							if(name.equalsIgnoreCase(allFluids[i].getName()) || name.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Fluid Name --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Fluid Name (Brake-Oil | Engine-Oil | Transmission-Oil | Coolant) : ");
							name = br.readLine();
						}
					}
					scanner = false;
					
					System.out.print("Enter the Fliud Brand (Toyota | Caltex) : ");
					brand = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allFluids.length ; i++) {
							if(brand.equalsIgnoreCase(allFluids[i].getBrand()) || brand.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Vehicle Brand --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Vehicle Brand (Toyota | BWM | Nissan) : ");
							brand = br.readLine();
						}
					}
					scanner = false;
					
					System.out.print("Enter the Volume (4L | 500ml | 250ml) : ");
					volume = br.readLine();
					while (!scanner) {
						for(int i = 0 ; i < allFluids.length ; i++) {
							if(volume.equalsIgnoreCase(allFluids[i].getVolume()) || volume.length() == 0) {
								scanner = true;
							}
						}
						
						if(!scanner) {
							System.out.println("-------------- Invalid Color Name --------------");
							System.out.println("Please Try Again\n");
							System.out.print("Enter the Volume (2L | 4L) : ");
							volume = br.readLine();
						}
					}
					scanner = false;
					
					if(name.length() != 0 && brand.length() != 0 && volume.length() != 0) {
						status = vehiclePartService.checkFluidAvailability(name, brand, volume);
					}
					else if(name.length() != 0 && brand.length() != 0 && volume.length() == 0) {
						status = vehiclePartService.checkFluidByNameNBrand(name, brand);
					}
					else if(name.length() == 0 && brand.length() != 0 && volume.length() != 0) {
						status = vehiclePartService.checkFluidByBrandNVolume(brand, volume);
					}
					else if(name.length() != 0 && brand.length() == 0 && volume.length() != 0) {
						status = vehiclePartService.checkFluidByNameNVolume(name, volume);
					}
					else if(name.length() != 0 && brand.length() == 0 && volume.length() == 0) {
						status = vehiclePartService.checkFluidByName(name);
					}
					else if(name.length() == 0 && brand.length() != 0 && volume.length() == 0) {
						status = vehiclePartService.checkFluidByBrand(brand);
					}
					else if(name.length() == 0 && brand.length() == 0 && volume.length() != 0) {
						status = vehiclePartService.checkFluidByVolume(volume);
					}
					
					//Fluid fl1 = new Fluid("Brake-Oil", "Toyota" , "250ml" , 2200);
					
					if(status) {
						fluids = vehiclePartService.getFluids();
						
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("Following fluid(s) is/are available!!!");
						System.out.println("-------------------------------------");
						
						for(int i = 0 ; i < vehiclePartService.getCount() ; i++) {
							System.out.println("Name - " + fluids[i].getName());
							System.out.println("Brand - " + fluids[i].getBrand());
							System.out.println("Volume - " + fluids[i].getVolume());
							System.out.println("Price - " + decfor.format(fluids[i].getPrice()));
							System.out.println("-------------------------------------");
						}
					}
					else if (allEmpty){
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("No Items has been selected");
						System.out.println("-------------------------------------");
					}
					else{
						System.out.println("");
						System.out.println("-------------------------------------");
						System.out.println("Item(s) you are looking for is/are Currently not available.");
						System.out.println("-------------------------------------");
					}
					
					System.out.println("");
					select = 0;
				}
				else if (select == 4) {
					System.out.println("\n------------------------------------");
					System.out.println("Shutting Down!!");
					System.out.println("------------------------------------");
					break;
				}
				
				System.out.println("");
			}
			
		}catch(Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good-Bye");
		bundleContext.ungetService(serviceRef);
	}

}
