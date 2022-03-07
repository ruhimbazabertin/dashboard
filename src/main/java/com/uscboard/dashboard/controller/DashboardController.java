package com.uscboard.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {
    @GetMapping("/user/index")
    public String dashboard(){

        return "dashboard/index";
    }
}
