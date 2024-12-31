package com.azure.samples.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ToDoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }
}