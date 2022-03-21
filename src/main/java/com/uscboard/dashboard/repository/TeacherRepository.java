package com.uscboard.dashboard.repository;

import com.uscboard.dashboard.model.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    
}
