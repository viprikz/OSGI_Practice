package org.itmo.helloclient;

import org.itmo.helloservice.Greeting;
import org.apache.felix.scr.annotations.*;
@Component(name = "org.itmo.helloclient.Client",immediate = true)
public class Client {
    /*@Reference(
            service = Greeting.class,
            cardinality = ReferenceCardinality.MANDATORY,
            unbind = "unsetGreeting",
            policy = ReferencePolicy.STATIC
    )*/
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY,policy = ReferencePolicy.STATIC)
    public Greeting greeting;

    @Activate
    protected void onActivate() {
        System.out.println("Stage 3. Client activated");
        greeting.sayHello();
    }

    protected void unbindGreeting() {
        this.greeting = null;
    }


}