package com.uscboard.dashboard.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.uscboard.dashboard.model.Student;
import com.uscboard.dashboard.repository.StudentRepository;
import com.uscboard.dashboard.util.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public void create(Student student, @RequestParam("file") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        repo.save(student);
     //UPLOAD PICTURE IN DIRECTORY
     String uploadDir = "uploads/" + student.getId();
     FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
    public List<Student> findAvalaibleStudents(){
        return repo.findAll();
    }
    public Optional<Student> findCourseById(int id){

        return repo.findById(id);
    }
    public void deleteCourseById(int id){
        repo.deleteById(id);
    }

}
