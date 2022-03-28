package com.uscboard.dashboard.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private LocalDate createdAt;

    //creating relationship between FACULTY AND DEPARTEMENT
    @OneToMany(
        mappedBy = "faculty",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Course> courses = new ArrayList<>();
    @JsonBackReference
    @OneToMany(
        mappedBy = "faculty",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Department> departents = new ArrayList<>();

    @OneToMany(
        mappedBy = "faculty",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Student> students = new ArrayList<>();

    //CREATE A RELATIONSHIP BETWEEN FACULTY AND TEACHER
    @OneToMany(
        mappedBy = "faculty",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Teacher> teachers = new ArrayList<>();

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public List<Department> getDepartents() {
        return departents;
    }
    public void setDepartents(List<Department> departents) {
        this.departents = departents;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    
    
}
