package com.example.tobyspringboot;

import com.example.config.MySpringBootApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@MySpringBootApplication
public class TobyspringbootApplication {


    public static void main(String[] args){
        //MySpringBootApplicationRunner.run(TobyspringbootApplication.class, args);
        SpringApplication.run(TobyspringbootApplication.class, args);
    }

    @Component
    static class Runner implements ApplicationRunner{
        private final Environment environment;
        public Runner(Environment environment){
            this.environment = environment;
        }
        @Override
        public void run(ApplicationArguments args) throws Exception {
            String name = environment.getProperty("my.name");
            System.out.println("my.name: " + name);
        }
    }
}
