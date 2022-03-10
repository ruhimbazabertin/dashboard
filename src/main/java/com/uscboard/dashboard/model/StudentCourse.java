// package com.uscboard.dashboard.model;

// import java.time.LocalDate;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;
// import javax.validation.constraints.Pattern;

// import org.springframework.format.annotation.DateTimeFormat;

// @Entity
// @Table(name = "students_courses")
// public class StudentCourse {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int id;
//     //create relationship
//     @ManyToOne
//     @JoinColumn(name = "student_id")
//     private Student student;
//     //create relationship
//     @ManyToOne
//     @JoinColumn(name = "course_id")
//     private Course course;
//     // @DateTimeFormat(pattern = "yyyy-MM-dd")
//     // private LocalDate assignedDate;

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public Student getStudent() {
//         return student;
//     }

//     public void setStudent(Student student) {
//         this.student = student;
//     }

//     public Course getCourse() {
//         return course;
//     }

//     public void setCourse(Course course) {
//         this.course = course;
//     }
    
// }
