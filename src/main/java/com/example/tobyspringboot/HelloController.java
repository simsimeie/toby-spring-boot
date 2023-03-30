package com.example.tobyspringboot;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }
    public String hello(String name){
        return helloService.hello(Objects.requireNonNull(name));
    }
}
