package com.uscboard.dashboard.repository;

import com.uscboard.dashboard.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course,Integer> {
}
