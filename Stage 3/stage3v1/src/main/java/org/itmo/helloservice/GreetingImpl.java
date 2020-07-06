package org.itmo.helloservice;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

@Service
@Component(immediate = true)
public class GreetingImpl implements Greeting {

    public void sayHello() {
        System.out.println("Hello,OSGi(stage 3)!");
    }
}