package com.uscboard.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

   Logger log = LoggerFactory.getLogger(this.getClass());
 
    @GetMapping("/login")
    public String authenticationPage(){
        log.info("Admin try to login into the system");

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
    
    log.info("Admin is into the system");

    return "redirect:/dashboard/user/index";

}
}
