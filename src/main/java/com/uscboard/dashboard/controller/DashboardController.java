package com.uscboard.dashboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uscboard.dashboard.model.Course;
import com.uscboard.dashboard.model.Department;
import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.repository.StudentRepository;
import com.uscboard.dashboard.service.CourseService;
import com.uscboard.dashboard.service.DepartmentService;
import com.uscboard.dashboard.service.StudentService;
import com.uscboard.dashboard.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DepartmentService departservice;
    @Autowired
    private UserService userService;

    @GetMapping("/user/index")
    public String dashboard(Model model){

        List<Student> studentsAvailable = studentService.findAvalaibleStudents();
        List<Course> coursesAvailable  = courseService.findAvalaibleCourses();
        List<User>   usersAvailable    = userService.findAllUsers();
        int totalOfStudentAvailable = studentsAvailable.size();
        int totalOfCourseAvailable  = coursesAvailable.size();
        int totalOfUserAvailable    = usersAvailable.size();
        //List of departments
     List<Department> departments = departservice.findAllDepartments();
        List<String>departNames = new ArrayList<>();
        List<Integer>studentNumberOnEachDepart = new ArrayList<>();
        List<Integer>coursesNumberOnEachDepart = new ArrayList<>();
        for(Department depart : departments){
           int studentNo = depart.getStudents().size();
           int courseNo =  depart.getCourses().size();
           studentNumberOnEachDepart.add(studentNo);
           coursesNumberOnEachDepart.add(courseNo);
        }
        for(Department depart : departments){
            String departName = depart.getName();
            departNames.add(departName);
        }

       //Send the list of departments to frontend
        model.addAttribute("departNames", departNames);
        model.addAttribute("studentNumberOnEachDepart", studentNumberOnEachDepart);
        model.addAttribute("coursesNumberOnEachDepart", coursesNumberOnEachDepart);

        model.addAttribute("totalOfStudentAvailable", totalOfStudentAvailable);
        model.addAttribute("totalOfCourseAvailable", totalOfCourseAvailable);
        model.addAttribute("totalOfUserAvailable", totalOfUserAvailable);
        model.addAttribute("users", usersAvailable);

        return "dashboard/index";
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        User user = userService.findUserById(id)
                                .orElseThrow(()->new IllegalArgumentException("Invalid id"+id));
            userService.deleteUser(user);
        return "dashboard/index";
    }
    @GetMapping("/users/edit/{id}")
    @ResponseBody
    public User editUser(@PathVariable("id") int id){
            User user = userService.findUserById(id)
                                    .orElseThrow(()->new IllegalArgumentException("Invalid user id."+id));
        return user;
    }
    @PostMapping("/users/update")
    public String updateUser(User user){
        userService.createUser(user);
        return"dashboard/index";
    }
}
