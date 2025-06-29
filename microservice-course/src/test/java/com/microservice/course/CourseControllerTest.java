package com.microservice.course;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.microservice.course.controller.CourseController;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.model.Course;
import com.microservice.course.service.ICourseService;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseService courseService;

    @Test
    void testFindAll() throws Exception {
        // Configura el comportamiento simulado del servicio
        when(courseService.findAll()).thenReturn(Collections.emptyList());

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/api/v1/course/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindById() throws Exception {
        // Crea un curso simulado
        Course course = new Course();
        course.setId(1L);
        course.setName("Curso de Prueba");
        course.setTeacher("Profesor de Prueba");

        // Configura el comportamiento simulado del servicio
        when(courseService.findById(1L)).thenReturn(course);

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/api/v1/course/search/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Curso de Prueba"))
                .andExpect(jsonPath("$.teacher").value("Profesor de Prueba"));
    }

    @Test
    void testSaveCourse() throws Exception {
        // Crea un curso simulado
        Course course = new Course();
        course.setName("Curso de Prueba");
        course.setTeacher("Profesor de Prueba");

        // Configura el comportamiento simulado del servicio
        Mockito.doNothing().when(courseService).save(any(Course.class));

        // Realiza la solicitud POST y verifica la respuesta
        mockMvc.perform(post("/api/v1/course/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(course)))
                .andExpect(status().isCreated());
    }

    @Test
    void testFindStudentsByIdCourse() throws Exception {
        
        StudentByCourseResponse response = StudentByCourseResponse.builder()
                .courseName("Curso de Prueba")
                .teacher("Profesor de Prueba")
                .studentDTOList(Collections.emptyList())
                .build();

        Mockito.when(courseService.findStudentsByIdCourse(1L)).thenReturn(response);

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/api/v1/course/search-student/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.courseName").value("Curso de Prueba"))
                .andExpect(jsonPath("$.teacher").value("Profesor de Prueba"))
                .andExpect(jsonPath("$.studentDTOList").isArray());
    }
}
