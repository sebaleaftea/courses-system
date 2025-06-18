package com.microservice.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.*;

import com.microservice.student.model.Student;
import com.microservice.student.repository.IStudentRepository;
import com.microservice.student.repository.StudentRepository;

import jakarta.persistence.Column;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class StudentServiceTest {

    @Autowired
    private IStudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testFindAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(new Student("John Doe", "john.doe@example.com", 25)));

        List<Student> students = studentService.findAllStudents();
        
    }

}
