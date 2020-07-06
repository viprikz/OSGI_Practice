package itmo.practice;
import org.osgi.framework.BundleContext;


public class Hello_command {
    private final BundleContext bundleContext;

    public Hello_command(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public void hello() {
        System.out.println("Enter param, please");
    }

    public void hello(String param) {
        System.out.println("Hello, " + param + "!");
    }
}
