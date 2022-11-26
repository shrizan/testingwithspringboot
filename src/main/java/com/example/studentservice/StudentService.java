package com.example.studentservice;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    @Cacheable("student")
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
}
