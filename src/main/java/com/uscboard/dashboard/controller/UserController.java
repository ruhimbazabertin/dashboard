package com.uscboard.dashboard.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
 
    @GetMapping("/login")
    public String authenticationPage(){
        return "auth/authentication";
    }

    @GetMapping("/default")
public String redirectionAfterLogin(HttpServletRequest request){
    // System.out.println("Here We are!!!");
    // if(request.isUserInRole("ROLE_ADMIN")){
    //     System.out.println("Never Reach out!");
    //     return "redirect:/course/index";
    // }else if(request.isUserInRole("ROLE_STUDENT_MANAGER")){
    //     return "redirect:/student/index";
    // }else if(request.isUserInRole("ROLE_COURSE_MANAGER")){
    //     return "redirect:/course/index";
    // }
    // else{
    //     return "redirect:/login";
    // }
    return "redirect:/student/index";

}
    @GetMapping("/user/reset-password")
    public String resetPasswordPage(){
        return "auth/resetPassword";
    }
}
