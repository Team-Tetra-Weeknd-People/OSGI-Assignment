package com.mtit.mall.database;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DBActivator implements BundleActivator {

	private static BundleContext context;
	
	MallDB db = new MallDBImpl();

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		db.connection();
		DBActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Database Connection Terminated!");
		DBActivator.context = null;
	}

}
