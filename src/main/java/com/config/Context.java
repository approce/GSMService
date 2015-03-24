package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com")
public class Context {

    public static void main(String[] args) {
        SpringApplication.run(Context.class, args);
    }
}
