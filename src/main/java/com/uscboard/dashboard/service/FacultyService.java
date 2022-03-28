package com.uscboard.dashboard.service;

import java.util.List;
import java.util.Optional;

import com.uscboard.dashboard.model.Faculty;
import com.uscboard.dashboard.repository.FacultyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepo;
    public void createFaculty(Faculty faculty){
        faculty.setCreatedAt(java.time.LocalDate.now());
        facultyRepo.save(faculty);
    }
    public List<Faculty> findAllFaculty(){

        return facultyRepo.findAll();
        
    }
    public Optional<Faculty> findFacultyById(int id){

        return facultyRepo.findById(id);
    }
}
