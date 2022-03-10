package com.uscboard.dashboard.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.uscboard.dashboard.model.Course;
import com.uscboard.dashboard.model.Student;
//import com.uscboard.dashboard.model.StudentCourse;
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
        System.out.println("Here We are!!");
        Student student = assignService.getById(id);
        System.out.println("STUDENT INFO"+student.getFirstName());
        try {
            if(student != null){
                List<Course> studentCoursesAvailable = student.getCourses();
                List<Course> courses = courseService.findAvalaibleCourses();
                model.addAttribute("student", student);
                model.addAttribute("courses", courses);
                model.addAttribute("availableCourses", studentCoursesAvailable);
                System.out.println("Something is going well...");
                return "assign/index";
               }
        } catch (Exception e) {
            System.out.println("Something wrong here...");
            System.out.println("ERROR" +e.getMessage());
            System.out.println("ERROR" +e.getCause());
        }
        return "assign/index";
    }
    @PostMapping("/create-student-course")
    public String createStudentCourse(@ModelAttribute("student") Student student, Model model){
        List<Course> selectedCourses = student.getCourses();
        Student st = new Student();
        st = studentService.getById(student.getId());
        List<Course> existingCourses = new ArrayList<>();
        existingCourses = st.getCourses();
        List<Course> appendCourses = new ArrayList<>();

        //update courses assigned to a student

        if((existingCourses.size()) == 0 && (student != null)){
            student.setCourses(selectedCourses);
            assignService.saveStudentCourses(student);
             logService.loggingInfo("Admin assigned courses to "+student.getFirstName() +" "+student.getLastName());
             System.out.println("Here Change: DEAL DONE!!!");
        }else if(student != null){
                appendCourses.addAll(existingCourses);
                appendCourses.addAll(selectedCourses);
                student.setCourses(appendCourses);
                assignService.saveStudentCourses(student);
                logService.loggingInfo("Admin assigned courses to "+student.getFirstName() +" "+student.getLastName());

        }else{
            logService.loggingError("Something went wrong");
            model.addAttribute("message", "Something went wrong");
        }
       return "redirect:/student/index";
    }
}
