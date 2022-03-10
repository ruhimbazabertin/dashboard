package com.uscboard.dashboard.service;

import java.util.List;

import com.uscboard.dashboard.model.Department;
import com.uscboard.dashboard.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;

    public void createDepartment(Department department){

        departmentRepo.save(department);
    }
    public List<Department> findAllDepartments(){

        return departmentRepo.findAll();
        
    }

}
