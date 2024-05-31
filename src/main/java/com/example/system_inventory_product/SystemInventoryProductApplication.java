package com.example.system_inventory_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SystemInventoryProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemInventoryProductApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


}
