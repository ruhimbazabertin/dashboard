package com.uscboard.dashboard.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.uscboard.dashboard.model.Course;
import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.model.StudentCourse;
import com.uscboard.dashboard.repository.StudentRepository;
import com.uscboard.dashboard.service.AssignService;
import com.uscboard.dashboard.service.CourseService;
import com.uscboard.dashboard.service.LoggingService;
import com.uscboard.dashboard.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssignController {
    @Autowired
    private AssignService assignService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
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
            List<Course> courses = courseService.findAvalaibleCourses();
            model.addAttribute("student", student);
            model.addAttribute("courses", courses);
            
        return "assign/index";
    }
    @PostMapping("/create-student-course")
    public String createStudentCourse(@ModelAttribute("student") Student student){

        //testing
        System.out.println("Name"+student.getFirstName());
       return "redirect:/student/index";
    }
}
