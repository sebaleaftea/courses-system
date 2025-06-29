package com.microservice_enrollment;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.microservice_enrollment.controller.EnrollmentController;
import com.microservice_enrollment.http.response.StudentByEnrollmentResponse;
import com.microservice_enrollment.model.Enrollment;
import com.microservice_enrollment.service.IEnrollmentService;

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEnrollmentService enrollmentService;

    @Test
    void testFindAllEnrollment() throws Exception {
        
        when(enrollmentService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/enrollment/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

    }

    @Test
    void testFindById() throws Exception {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        when(enrollmentService.findById(1L)).thenReturn(enrollment);

        mockMvc.perform(get("/api/v1/enrollment/search/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.pay").value("true"))
                .andExpect(jsonPath("$.expiration").value("1 mes"))
                .andExpect(jsonPath("$.status").value("curso iniciado"));

    }

    @Test
    void testSaveEnrollment() throws Exception {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        Mockito.doNothing().when(enrollmentService).save(any(Enrollment.class));

        mockMvc.perform(post("/api/v1/enrollment/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(enrollment)))
                .andExpect(status().isCreated());

    }

    @Test
    void testFindStudentByIdEnrollment() throws Exception {
        
        StudentByEnrollmentResponse response = StudentByEnrollmentResponse.builder()
        .pay("true")
        .expiration("1 mes")
        .status("curso iniciado")
        .studentDTOList(Collections.emptyList())
        .build();

        Mockito.when(enrollmentService.findStudentsByIdEnrollment(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/enrollment/search-student/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pay").value("true"))
                .andExpect(jsonPath("$.expiration").value("1 mes"))
                .andExpect(jsonPath("$.status").value("curso iniciado"))
                .andExpect(jsonPath("$.studentDTOList").isArray());

    }        
}
