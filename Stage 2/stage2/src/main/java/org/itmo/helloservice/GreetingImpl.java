package org.itmo.helloservice;

import org.itmo.helloservice.Greeting;

public class GreetingImpl implements Greeting {
    final String m_name;

    public GreetingImpl(String name) {
        m_name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + m_name + "world!");
    }
}