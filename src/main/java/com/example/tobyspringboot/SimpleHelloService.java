package com.example.tobyspringboot;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService{

    @Override
    public String hello(String name){
        return "Hello " + name;
    }
}
