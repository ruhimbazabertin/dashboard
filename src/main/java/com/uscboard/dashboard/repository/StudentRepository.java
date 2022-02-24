package com.uscboard.dashboard.repository;

import java.util.List;

import com.uscboard.dashboard.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student,Integer> {

@Query(value="SELECT * FROM STUDENT s WHERE s.first_name like %:keyword%", nativeQuery = true)
List<Student> findByKeyword(@Param("keyword") String keyword);
}
