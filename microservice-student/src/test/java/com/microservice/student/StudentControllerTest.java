package com.microservice.student;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.student.controller.StudentController;
import com.microservice.student.model.Student;
import com.microservice.student.service.IStudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student();
        student.setName("Juan");

        mockMvc.perform(post("/api/v1/student/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated());
    }

    @Test
    void testFindAllStudents() throws Exception {
        Mockito.when(studentService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/student/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Juan");
        Mockito.when(studentService.findById(1L)).thenReturn(student);

        mockMvc.perform(get("/api/v1/student/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Juan"));
    }

    @Test
    void testFindByIdCourse() throws Exception {
        Mockito.when(studentService.findByIdCourse(2L)).thenReturn(List.of(new Student()));

        mockMvc.perform(get("/api/v1/student/search-course/2"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdEnrollment() throws Exception {
        Mockito.when(studentService.findByIdEnrollment(3L)).thenReturn(List.of(new Student()));

        mockMvc.perform(get("/api/v1/student/search-enrollment/3"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdCertificate() throws Exception {
        Mockito.when(studentService.findByIdCertificate(4L)).thenReturn(List.of(new Student()));

        mockMvc.perform(get("/api/v1/student/search-certificate/4"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdSupport() throws Exception {
        Mockito.when(studentService.findByIdSupport(5L)).thenReturn(List.of(new Student()));

        mockMvc.perform(get("/api/v1/student/search-support/5"))
                .andExpect(status().isOk());
    }
}
