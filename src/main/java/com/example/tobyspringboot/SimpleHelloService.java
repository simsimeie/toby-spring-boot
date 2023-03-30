package com.example.tobyspringboot;

public class SimpleHelloService implements HelloService{

    @Override
    public String hello(String name){
        return "Hello " + name;
    }
}
