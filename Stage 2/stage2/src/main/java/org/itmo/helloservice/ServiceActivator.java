package org.itmo.helloservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ServiceActivator implements BundleActivator {

    public void start(BundleContext bundleContext) {
        System.out.println("Bundle active");
        bundleContext.registerService(Greeting.class.getName(), new GreetingImpl("service"), null);
    }

    public void stop(BundleContext ctx) {
    }
}
