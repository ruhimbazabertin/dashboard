package com.uscboard.dashboard.repository;

import com.uscboard.dashboard.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {



}
