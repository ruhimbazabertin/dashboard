package com.uscboard.dashboard.repository;

import com.uscboard.dashboard.model.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    
}
