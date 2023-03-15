package sa_osgi_toysservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ProducerActivator implements BundleActivator {

	ServiceRegistration Register;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Service successfully started");
		ToysService toysService = new ToysServiceImpl();
		Register = bundleContext.registerService(ToysService.class.getName(), toysService, null);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Service successfully stopped");
		Register.unregister();
	}

}
