package com.microservice.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.microservice.student.model.Student;

public class StudentTest {

    @Test
    void testGettersAndSetters() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Juan");
        student.setLastName("Pérez");
        student.setEmail("juan@mail.com");
        student.setCourseId(10L);
        student.setCertificateId(20L);
        student.setEnrollmentId(30L);
        student.setSupportId(40L);

        assertEquals(1L, student.getId());
        assertEquals("Juan", student.getName());
        assertEquals("Pérez", student.getLastName());
        assertEquals("juan@mail.com", student.getEmail());
        assertEquals(10L, student.getCourseId());
        assertEquals(20L, student.getCertificateId());
        assertEquals(30L, student.getEnrollmentId());
        assertEquals(40L, student.getSupportId());
    }

    @Test
    void testBuilder() {
        Student student = Student.builder()
                .id(2L)
                .name("Ana")
                .lastName("García")
                .email("ana@mail.com")
                .courseId(11L)
                .certificateId(21L)
                .enrollmentId(31L)
                .supportId(41L)
                .build();

        assertEquals(2L, student.getId());
        assertEquals("Ana", student.getName());
        assertEquals("García", student.getLastName());
        assertEquals("ana@mail.com", student.getEmail());
        assertEquals(11L, student.getCourseId());
        assertEquals(21L, student.getCertificateId());
        assertEquals(31L, student.getEnrollmentId());
        assertEquals(41L, student.getSupportId());
    }

    @Test
    void testAllArgsConstructorAndNoArgsConstructor() {
        Student student1 = new Student(3L, "Luis", "Martínez", "luis@mail.com", 12L, 22L, 32L, 42L);
        assertEquals("Luis", student1.getName());

        Student student2 = new Student();
        assertNull(student2.getName());
    }
}