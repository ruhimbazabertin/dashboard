package com.uscboard.dashboard.controller;

import java.util.List;
import java.util.Optional;

import com.uscboard.dashboard.model.Faculty;
import com.uscboard.dashboard.model.Teacher;
import com.uscboard.dashboard.service.FacultyService;
import com.uscboard.dashboard.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherController {
    @Autowired
    private FacultyService facultyservice;
    @Autowired
    private TeacherService teacherservice;

    @GetMapping("/teacher/index")
    public String index(Model model){
        Teacher teacher = new Teacher();
        List<Faculty> faculties = facultyservice.findAllFaculty();
        List<Teacher> teachers  = teacherservice.findAllTeacher();
        model.addAttribute("teacher", teacher);
        model.addAttribute("teachers", teachers);
        model.addAttribute("faculties", faculties);
        return"teacher/index";
    }
    @PostMapping("/teacher/create")
    public String createTeacher(@ModelAttribute("teacher") Teacher teacher){
        teacherservice.createTeacher(teacher);
        return"redirect:/teacher/index";
    }
    //Find course and return back with json data.
    @GetMapping("/teachers/edit/{id}")
    @ResponseBody
    public Teacher findTeacher(@PathVariable("id") int id){

        Teacher teacher = teacherservice.findTeacherById(id)
                                    .orElseThrow(()->new IllegalArgumentException("Invalid Teacher ID"+ id));

        return teacher;
    }
    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id){

                 teacherservice.findTeacherById(id)
                                    .orElseThrow(()->new IllegalArgumentException("Invalid Teacher ID"+ id));
                 teacherservice.deleteTeacherById(id);

                 return"redirect:/teacher/index";
    }
    
}
