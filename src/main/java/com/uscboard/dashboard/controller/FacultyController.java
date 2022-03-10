package com.uscboard.dashboard.controller;

import java.util.List;

import com.uscboard.dashboard.model.Faculty;
import com.uscboard.dashboard.service.FacultyService;
import com.uscboard.dashboard.service.LoggingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private LoggingService logService;

    @GetMapping("/faculty/form")
    public String showFacultyFormPage(Model model){
        Faculty faculty = new Faculty();
        model.addAttribute("faculty", faculty);
        //fetch all faculty and display them in the page
        List<Faculty> faculties = facultyService.findAllFaculty();
        model.addAttribute("faculties", faculties);
        return "faculty/createFaculty";
    }
    @PostMapping("/faculty/create")
    public String createFaculty(@ModelAttribute("faculty") Faculty faculty, Model model){
        try {
            if(faculty != null){
                facultyService.createFaculty(faculty);
                logService.loggingInfo("Admin created a new Faculty");
            }else{
                model.addAttribute("message", "Something went wrong in creating Faculty.");
            }
        } catch (Exception e) {
            logService.loggingError("Something went wrong in creating Faculty.");
            e.printStackTrace();
        }

        return "faculty/createFaculty";
    }
}
