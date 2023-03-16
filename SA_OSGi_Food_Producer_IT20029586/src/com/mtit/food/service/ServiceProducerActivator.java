package com.mtit.food.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceProducerActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceRegistration producerServiceReg;

	public void start(BundleContext bundleContext) throws Exception {
		FoodService foodService = new FoodServiceImpl();
		producerServiceReg = bundleContext.registerService(FoodService.class.getName(), foodService, null);
		System.out.println("Food Producer Connected");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Food Producer Disconnected");
		producerServiceReg.unregister();
	}

}
