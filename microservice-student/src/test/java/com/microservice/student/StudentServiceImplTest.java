package com.microservice.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;


import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;
import com.microservice.student.service.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
    void testFindAll() {
        Student student = new Student();
        when(studentRepository.findAll()).thenReturn(List.of(student));

        List<Student> result = studentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.findById(1L);

        assertNotNull(result);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.findById(2L));
        verify(studentRepository, times(1)).findById(2L);
    }

    @Test
    void testSave() {
        Student student = new Student();
        studentService.save(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testFindByIdCourse() {
        Student student = new Student();
        when(studentRepository.findAllByCourseId(1L)).thenReturn(List.of(student));

        List<Student> result = studentService.findByIdCourse(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAllByCourseId(1L);
    }

    @Test
    void testFindByIdEnrollment() {
        Student student = new Student();
        when(studentRepository.findAllByEnrollmentId(2L)).thenReturn(List.of(student));

        List<Student> result = studentService.findByIdEnrollment(2L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAllByEnrollmentId(2L);
    }

    @Test
    void testFindByIdCertificate() {
        Student student = new Student();
        when(studentRepository.findAllByCertificateId(3L)).thenReturn(List.of(student));

        List<Student> result = studentService.findByIdCertificate(3L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAllByCertificateId(3L);
    }

    @Test
    void testFindByIdSupport() {
        Student student = new Student();
        when(studentRepository.findAllBySupportId(4L)).thenReturn(List.of(student));

        List<Student> result = studentService.findByIdSupport(4L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAllBySupportId(4L);
    }
}