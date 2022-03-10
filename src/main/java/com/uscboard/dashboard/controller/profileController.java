package com.uscboard.dashboard.controller;

import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class profileController {
    @Autowired
    private UserService userservice;

    @GetMapping("/user/profile")
    public String userProfile(Authentication auth, Model model){
        //System.out.println(auth.getName());
        String username = auth.getName();
       User user = userservice.findLoggedUser(username);
        try {
            if(user != null){
                model.addAttribute("currentUser", user);
             return "profile/userProfile";
            }else{
                System.out.println("SOMETHING WENT WRONG.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "profile/userProfile";
    }
}
