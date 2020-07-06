package itmo.practice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class Activator implements BundleActivator {
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext ctx) throws Exception {
        Activator.context = ctx;
        Hashtable props = new Hashtable();
        props.put("osgi.command.scope", "itmo/practice");
        props.put("osgi.command.function", "hello");
        context.registerService(Hello_command.class.getName(), new Hello_command(context), props);
    }


    public void stop(BundleContext ctx) throws Exception {
        Activator.context = null;
    }

}
