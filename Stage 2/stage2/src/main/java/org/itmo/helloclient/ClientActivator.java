package org.itmo.helloclient;

import org.itmo.helloservice.Greeting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ClientActivator implements BundleActivator {
    public void start(BundleContext ctx) {
        ServiceReference ref = ctx.getServiceReference(Greeting.class.getName());
        ((Greeting) ctx.getService(ref)).sayHello();
    }
 
    public void stop(BundleContext ctx) {
    }
}