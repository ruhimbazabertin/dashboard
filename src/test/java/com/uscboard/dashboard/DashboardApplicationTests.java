package com.uscboard.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.uscboard.dashboard.model.Role;
import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.service.RoleService;
import com.uscboard.dashboard.service.UserService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class DashboardApplicationTests {

    @Autowired
    private RoleService roleservice;
    @Autowired
    private UserService userService;
    // @BeforeAll
    // @Test
    //  void createRole(){
    //     Role adminRole = new Role();
    //     Role courseManager = new Role();
    //     Role studentManager = new Role();
    //     //create role for admin:
    //     adminRole.setName("ADMIN");
    //     roleservice.createRole(adminRole);
    //     //create role for course manager:
    //     courseManager.setName("COURSE_MANAGER");
    //     roleservice.createRole(courseManager);
    //     //create role for student manager:
    //     studentManager.setName("STUDENT_MANAGER");
    //     roleservice.createRole(studentManager);
    //  }

    @BeforeAll
     @Test
     void createUser(){

         //find the role to atach to user
         Set<Role> roles1 = new HashSet<Role>();
        //List<Role> roles1 = new ArrayList<Role>();
         Role courseManagerRole = roleservice.findRoleById(33);
         roles1.add(courseManagerRole);
        User courseManager = new User();
        courseManager.setFirstName("Niyonzima");
        courseManager.setLastName("Theogene");
        courseManager.setGender("Male");
        courseManager.setEmail("theo@gmail.com");
        courseManager.setPassword("theo123");
        courseManager.setRoles(roles1);
        
    userService.createUser(courseManager);

     }

}

