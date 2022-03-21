package com.uscboard.dashboard.service;

import java.util.List;
import java.util.Optional;

import com.uscboard.dashboard.model.Teacher;
import com.uscboard.dashboard.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepo;
    public void createTeacher(Teacher teacher){
        //set creation time
        teacher.setCreatedAt(java.time.LocalDate.now());
        teacherRepo.save(teacher);
    }
    public List<Teacher> findAllTeacher(){
        
        return teacherRepo.findAll();
    }
    public Optional<Teacher> findTeacherById(int id){

        return teacherRepo.findById(id);
    }
    public void deleteTeacherById(int id){

        teacherRepo.deleteById(id);
    }
    
}
