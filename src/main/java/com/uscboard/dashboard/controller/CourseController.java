package com.uscboard.dashboard.controller;

import com.uscboard.dashboard.model.Course;
import com.uscboard.dashboard.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CourseController {
    @Autowired
    private CourseService service;

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

    @GetMapping("/course/index")
    public String index(Model model){
        List<Course>courses = service.findAvalaibleCourses();
        model.addAttribute("courses", courses);
        return "course/index";
    }
    @GetMapping("/course/form")
    public String showCourseForm(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/createCourse";
    }
    @PostMapping("/course/create")
    public String createCourse(@ModelAttribute("course") @Valid Course course, BindingResult result){
        if(result.hasErrors()){
           // return "redirect:/course/form";
            return "course/createCourse";
        }
        service.create(course);
        return"redirect:/course/index";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model){
        Course course = service.findCourseById(id)
                            .orElseThrow(()->new IllegalArgumentException("Invalid Course Id"+id));
                
            model.addAttribute("course", course);

        return "course/editCourse";
    }
    @PostMapping("/update/{id}")
    public String updateCourse(@Valid @ModelAttribute("course") Course course, @PathVariable("id") int id, BindingResult result){
        if(result.hasErrors()){
            return "course/editCourse";
        }
        course.setId(id);
        service.create(course);

        return"redirect:/course/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model){
           Optional<Course> course = service.findCourseById(id);
            if(course.isPresent()){
                service.deleteCourseById(id);
            }
        else{
            model.addAttribute("message", "The course, you are trying to delete is not available");
        }
        return"redirect:/course/index";
    }
}
