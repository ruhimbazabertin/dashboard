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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.validation.Valid;

import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("")
public class StudentController {
    Logger log = LoggerFactory.getLogger(this.getClass());
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
    public String index(Model model, String keyword){
        log.info("Admin viewing student information");
        List<Student> students = service.findAvalaibleStudents();
        List<Student> studentRequested = service.searchByKeyword(keyword);

        if(keyword != null){
            model.addAttribute("students", studentRequested);
        }else{
            model.addAttribute("students",students);
        }

        return"student/index";
    }
    @GetMapping("/form")
    public String showStudentForm(Model model){
        log.info("Admin Accessed the student form page");
        Student student = new Student();
        model.addAttribute("student", student);
        return"student/createStudent";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("student") @Valid Student student, 
     @RequestParam("file") MultipartFile multipartFile, BindingResult result) throws IOException{
        log.info("Admin is trying to register a new student");
        
        if(result.hasErrors()){
            log.error("Something went wrong in registering a new student");
                return"student/createStudent";
            }
            log.info("Admin created a new student");
            service.create(student, multipartFile);

        return"redirect:/student/index";
    }
    @GetMapping("/editStudent/{id}")
    public String editStudentForm(@PathVariable("id") int id,  Model model){
         Student student = service.findStudentById(id)
                          .orElseThrow(()->new IllegalArgumentException("Invalid student Id"+id));
                          log.warn("Student not found");

            model.addAttribute("student", student);
            log.info("Admin is trying to edit a student information");
        return "student/editStudent";
    }
    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@ModelAttribute("student") @Valid Student student, @PathVariable("id") int id,
    BindingResult result, @RequestParam("file") MultipartFile multipartFile)throws IOException{
        if(result.hasErrors()){
            log.error("Something went wrong in updating a student");
            return"student/createStudent";
        }
        student.setId(id);
        service.create(student, multipartFile);
        log.error("Admin updated a student propery");
        return"redirect:/student/index";
    }
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model){
        log.info("Admin is trying to delete a student");
        Optional<Student> student = service.findStudentById(id);
         if(student.isPresent()){
             service.deleteStudentById(id);
             log.info("Admin deleted a student");
         }
     else{
        log.warn("Admin, a student you want to delete is not available");
         model.addAttribute("message", "The student, you are trying to delete is not available");
     }
     return"redirect:/student/index";
     
 }
}
