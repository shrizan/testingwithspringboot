package com.example.studentservice;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @DisplayName("Return saved student from service layer")
    @Test
    void getStudentById_forSavedStudent_isReturned() {
        // give
        var savedStudent = studentRepository.save(new Student(null, "Mark"));

        //when
        Student student = studentService.getStudentById(savedStudent.getId());
        //then
        then(student.getName()).isEqualTo("Mark");
        then(student.getId()).isNotNull();
    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown() {
        // given
        int id = 1234;
        // when
        var throwable = catchThrowable(() -> studentService.getStudentById(id));
        // then
        then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
