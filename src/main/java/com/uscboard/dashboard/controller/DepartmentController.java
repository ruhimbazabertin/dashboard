package com.uscboard.dashboard.controller;

import java.util.List;

import com.uscboard.dashboard.model.Department;
import com.uscboard.dashboard.model.Faculty;
import com.uscboard.dashboard.service.DepartmentService;
import com.uscboard.dashboard.service.FacultyService;
import com.uscboard.dashboard.service.LoggingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {
    @Autowired
    private LoggingService logservice;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/department/form")
    public String showDepartmentForm(Model model){
        Department department = new Department();
        List<Faculty> faculties = facultyService.findAllFaculty();
        List<Department> departments = departmentService.findAllDepartments();

        model.addAttribute("faculties", faculties);
        model.addAttribute("department", department);
        model.addAttribute("departments", departments);

        return "department/createDepartment";
    }
    @PostMapping("/department/create")
    public String createDepartment(@ModelAttribute("department") Department department, Model model){
        try {
            if(department != null){
                departmentService.createDepartment(department);
                logservice.loggingInfo("Admin created a new departement.");
                return "redirect:/department/form";
            }else{
                model.addAttribute("message", "Something went wrong");
            }
        } catch (Exception e) {
            logservice.loggingError("Something went wrong in creating in deparment");
            e.printStackTrace();
        }
        return "department/createDepartment";
    }
    
}
