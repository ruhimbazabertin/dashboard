package com.uscboard.dashboard.controller;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.uscboard.dashboard.exception.UserNotFoundException;
import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.service.LoggingService;
import com.uscboard.dashboard.service.UserDetailServiceImpl;
import com.uscboard.dashboard.util.ApplicationUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
    @Autowired
    private LoggingService logservice;
     @Autowired
     private UserDetailServiceImpl userservice;
     @Autowired
     private JavaMailSender mailSender;

    @GetMapping("/forgot-password")
    public String resetPasswordPage(){
        return "auth/resetPassword";
    }
    @PostMapping("/forgot-password")
    public String sendToken(HttpServletRequest request, Model model){
        logservice.loggingInfo("User trying to reset password using email");
        String email = request.getParameter("email");

        //generate random string
        String token = RandomString.make(30);

        //update token based user email
        try {
            userservice.updateResetPasswordToken(token, email);
            logservice.loggingInfo("System generated token based on email provided");
            //generate reset password link
            String resetPasswordLink =  "localhost:8080/reset-password?token=" +token;
            System.out.println(resetPasswordLink);
            //send email
            sendingEmail(email, resetPasswordLink);

            model.addAttribute("message","We have sent a reset password link to your email. Please check.");


        } catch (UserNotFoundException ex){
            model.addAttribute("error", ex.getMessage());
            logservice.loggingError("This email is not recognized by the system.");
        }catch(UnsupportedEncodingException | MessagingException ex){
            model.addAttribute("error", "Error while sending email.");
            logservice.loggingError("Error while sending email");
        }

            return "auth/resetPassword";
      }
      //method responsible to send email to the user
      private void sendingEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException{
          MimeMessage message = mailSender.createMimeMessage(); 
          MimeMessageHelper helper = new MimeMessageHelper(message);

          helper.setFrom("support@uscdashboard.com", "uscdashboard");
          helper.setTo(email);

          String subject = "Here's the link to reset your password";

          String content = "<p>Hello,</p>" 
                    + "<p>You have requested to reset your password.</p>"
                    + "<p>Click the link below to change your password:</p>"
                    + "<p><b><a href=\"" + resetPasswordLink + "\">Change my password</a></b></p>"
                    + "<p>Ignore this email if you do remember your password, or you have not made the request</p>";

            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
      }
      @GetMapping("/reset-password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model){
        System.out.println("1");
        User user = userservice.getByresetPasswordToken(token);
        System.out.println("22");
        if(user == null){
            logservice.loggingWarn("Invalid Token");
            model.addAttribute("message", "Invalid Token");
            System.out.println("User not available");
            return "message";
        }
        model.addAttribute("token", token);

        return "auth/changePassword";
    }
    @PostMapping("/reset-password")
    public String processResetPassword(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userservice.getByresetPasswordToken(token);
        if(user == null){
            logservice.loggingWarn("Invalid Token");
            model.addAttribute("message", "Invalid Token");
            return "auth/changePassword";
        }else{
            userservice.updatePassword(user, password);
            logservice.loggingInfo("User changed the Password");
            model.addAttribute("message", "You have successfully changed your password.");
            return "auth/authentication";
        }
    

    }
}
