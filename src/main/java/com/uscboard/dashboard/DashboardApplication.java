package com.uscboard.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class DashboardApplication {



    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }
    
}
