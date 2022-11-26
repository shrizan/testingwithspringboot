package com.example.studentservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getStudentByName(String mark);

    @Query("select avg(grade) from Student where active=true")
    Double getAvgGradeForActiveStudents();
}
