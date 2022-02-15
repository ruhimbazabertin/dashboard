package com.uscboard.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.service.StudentService;
import com.uscboard.dashboard.util.FileUploadUtil;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
    @GetMapping("/student/index")
    public String index(Model model){
        List<Student> students = service.findAvalaibleStudents();
        model.addAttribute("students",students);
        return"student/index";
    }
    @GetMapping("/student/form")
    public String showStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return"student/createStudent";
    }

    @PostMapping("/student/create")
    public String create(@ModelAttribute("student") @Valid Student student, BindingResult result, @RequestParam("file") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        student.setPicture(fileName);   
        if(result.hasErrors()){
                return"student/createStudent";
            }
            service.create(student, multipartFile);

        return"redirect:/student/index";
    }
}
