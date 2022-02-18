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

@Controller
@RequestMapping("")
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
    @GetMapping("student/index")
    public String index(Model model){
        List<Student> students = service.findAvalaibleStudents();
        model.addAttribute("students",students);
        return"student/index";
    }
    @GetMapping("/form")
    public String showStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return"student/createStudent";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("student") @Valid Student student, 
     @RequestParam("file") MultipartFile multipartFile, BindingResult result) throws IOException{
        
        if(result.hasErrors()){
                return"student/createStudent";
            }
            service.create(student, multipartFile);

        return"redirect:/student/index";
    }
    @GetMapping("/editStudent/{id}")
    public String editStudentForm(@PathVariable("id") int id,  Model model){
         Student student = service.findStudentById(id)
                          .orElseThrow(()->new IllegalArgumentException("Invalid student Id"+id));

            model.addAttribute("student", student);
        return "student/editStudent";
    }
    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@ModelAttribute("student") @Valid Student student, @PathVariable("id") int id,
    BindingResult result, @RequestParam("file") MultipartFile multipartFile)throws IOException{
        if(result.hasErrors()){
            return"student/createStudent";
        }
        student.setId(id);
        service.create(student, multipartFile);
        return"redirect:/student/index";
    }
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model){
        Optional<Student> student = service.findStudentById(id);
         if(student.isPresent()){
             service.deleteStudentById(id);
         }
     else{
         model.addAttribute("message", "The student, you are trying to delete is not available");
     }
     return"redirect:/student/index";
     
 }
}
