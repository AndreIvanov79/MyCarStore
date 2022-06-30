package com.myspringapp.carsrentalstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication
public class CarsRentalStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsRentalStoreApplication.class, args);
    }
}