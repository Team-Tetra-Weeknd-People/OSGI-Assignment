package sa_osgi_clothingservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceProducerActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceRegistration serviceRegister;

	public void start(BundleContext context) throws Exception {
		ClothingService clothingService = new ClothingServiceImpl();
		serviceRegister = context.registerService(ClothingService.class.getName(), clothingService, null);
		System.out.println("Clothing Catalog Connected.");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Clothing Catalog Disconnected.");
		serviceRegister.unregister();
	}

}
