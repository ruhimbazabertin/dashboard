package com.uscboard.dashboard.repository;

import com.uscboard.dashboard.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}
