package com.uscboard.dashboard.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {
//   // @Autowired
//    //private EmailService emailservice;

//     @GetMapping("/user/reset-password")
//     public String resetPasswordPage(){
//         return "auth/resetPassword";
//     }
//     @PostMapping("/send/one-time-password")
//     public String sendToken(@RequestParam("email") String email, HttpSession session){
//         System.out.println("Your Email is "+email);

//         //generating ONE TIME PASSWORD of 4 digit
//         Random random = new Random(1000); 
//         int token = random.nextInt(999999);
//         System.out.println("This is your token"+token);

//         //write code for sending token to email
//         String subject = "Token from uscdashboard";
//         String message = "<h2> TOKEN = "+token+"</h2>";
//         String to   = email;
//         //sending data to email service
//        // boolean flag = this.emailservice.sendEmail(subject, message, to);

//         if(flag){
//             return "auth/verification";
//         }else{
//             session.setAttribute("message", "Check your your email wisely.");
//             return "auth/resetPassword";
//         }
//     }
    
}
