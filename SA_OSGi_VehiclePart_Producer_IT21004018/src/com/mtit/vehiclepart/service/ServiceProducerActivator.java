package com.mtit.vehiclepart.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceProducerActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceRegistration producerServiceReg;

	public void start(BundleContext bundleContext) throws Exception {
		
		VehiclePartsService vehiclePartService = new VehiclePartsServiceImpl();
		producerServiceReg = bundleContext.registerService(VehiclePartsService.class.getName(), vehiclePartService, null);
		System.out.println("Vehicle Parts Producer Connected");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Vehicle Parts Producer Disconnected");
		producerServiceReg.unregister();
	}

}
