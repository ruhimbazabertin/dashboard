package com.uscboard.dashboard;

import com.uscboard.dashboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.TemplateEngine;

@SpringBootApplication
public class DashboardApplication {



    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }
    
}
