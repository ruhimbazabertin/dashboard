package com.uscboard.dashboard;

import com.uscboard.dashboard.model.User;
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
    UserService userService;

@BeforeAll
    @Test
  void createUser(){
    User user = new User();
   // user.setId(2);
    user.setFirstName("Ahishakiye");
    user.setLastName("Aline");
    user.setGender("Male");
    user.setEmail("aline@gmail.com");
    user.setPassword("aline123");//aline123
    user.setActive(true);
    user.setRole("USER");
    
    userService.createUser(user);
 }

}
