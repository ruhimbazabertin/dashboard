package com.uscboard.dashboard.service;

import java.util.Optional;

import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.repository.CourseRepository;
import com.uscboard.dashboard.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignService {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private CourseRepository  courseRepo;

    public Optional<Student> findStudentById(int id){

        return studentRepo.findById(id);
    }
        public Student getStudentById(int id){

        return studentRepo.getById(id);
    }
    
}
