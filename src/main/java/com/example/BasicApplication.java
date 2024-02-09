package com.example;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BasicApplication {

	private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        // Store the Spring application context
        applicationContext = SpringApplication.run(BasicApplication.class, args);

        // Launch JavaFX application
        Application.launch(Javafxbase.class, args);
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
