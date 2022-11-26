package com.example.studentservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testGetStudentByName_returnsStudentDetails() {
        //given
        Student savedStudent = entityManager.persistFlushFind(new Student(null, "Mark"));
        //when
        Student student = studentRepository.getStudentByName("Mark");
        //then
        then(savedStudent.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());
    }

    @Test
    void getAvgGradeForActiveStudents_calculateAvg() {
        //given
        var mark = Student.builder().name("Mark").active(true).grade(80).build();
        var susan = Student.builder().name("Susan").active(true).grade(100).build();
        var peter = Student.builder().name("Peter").active(false).grade(50).build();
        List.of(mark,susan,peter).forEach(entityManager::persistFlushFind);

        //when
        Double avgGrade = studentRepository.getAvgGradeForActiveStudents();

        //then
        then(avgGrade).isEqualTo(90);
    }
}
