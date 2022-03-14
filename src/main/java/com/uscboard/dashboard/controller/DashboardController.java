package com.uscboard.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import com.uscboard.dashboard.model.Course;
import com.uscboard.dashboard.model.Department;
import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.repository.StudentRepository;
import com.uscboard.dashboard.service.CourseService;
import com.uscboard.dashboard.service.DepartmentService;
import com.uscboard.dashboard.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DepartmentService departservice;

    @GetMapping("/user/index")
    public String dashboard(Model model){

        List<Student> studentsAvailable = studentService.findAvalaibleStudents();
        List<Course> coursesAvailable  = courseService.findAvalaibleCourses();
        int totalOfStudentAvailable = studentsAvailable.size();
        int totalOfCourseAvailable  = coursesAvailable.size();
        //List of departments
     List<Department> departments = departservice.findAllDepartments();
        List<String>departNames = new ArrayList<>();
        List<Integer>studentNumberOnEachDepart = new ArrayList<>();
        for(Department depart : departments){
           // System.out.println("THE NAME OF DEPARTMENT: "+depart.getName());
           //int studentNo = depart.getStudents().size();
          // System.out.println("Number of student in each department: "+studentNo);
           studentNumberOnEachDepart.add(depart.getCourses().size());
        }
        for(Department depart : departments){
            String departName = depart.getName();
            departNames.add(departName);
        }
        
       //Send the list of departments to frontend
        model.addAttribute("departNames", departNames);
        model.addAttribute("studentNumberOnEachDepart", studentNumberOnEachDepart);

        model.addAttribute("totalOfStudentAvailable", totalOfStudentAvailable);
        model.addAttribute("totalOfCourseAvailable", totalOfCourseAvailable);

        return "dashboard/index";
    }
}
