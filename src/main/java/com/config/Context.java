package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@ComponentScan("com")
@ImportResource("classpath:aggregators.xml")
public class Context {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Context.class, args);
    }
}
