package com.example.tobyspringboot;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
@Component
public class HelloController {
    private final HelloService helloService;
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }
    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name){
        return helloService.hello(Objects.requireNonNull(name));
    }
}
