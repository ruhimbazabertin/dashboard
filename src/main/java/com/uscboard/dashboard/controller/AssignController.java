package com.uscboard.dashboard.controller;

import java.util.Optional;

import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.repository.StudentRepository;
import com.uscboard.dashboard.service.AssignService;
import com.uscboard.dashboard.service.LoggingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AssignController {
    @Autowired
    private AssignService assignService;
    @Autowired
    private LoggingService logService;

    @GetMapping("/assign-course-student/{id}")
    public String assignCourseIndex(@PathVariable("id") int id, Model model){
        //Optional<Student> student = assignService.findStudentById(id);
                    Student student  = assignService.getStudentById(id);
            // if(student.isEmpty()){
            //     logService.loggingInfo("A student you are looking for is not available");
            // }
            if(student == null){
                logService.loggingInfo("A student you are looking for is not available");
            }
            model.addAttribute("student", student);
            
        return "assign/index";
    }
}
