package com.microservice_certificate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice_certificate.controller.CertificateController;
import com.microservice_certificate.http.response.StudentByCertificateResponse;
import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.service.ICertificateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CertificateController.class)
public class CertificateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICertificateService certificateService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveCertificate() throws Exception {
        Certificate cert = new Certificate();
        cert.setName("Certificado Test");

        mockMvc.perform(post("/api/v1/certificate/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cert)))
                .andExpect(status().isCreated());
    }

    @Test
    void testFindAllCertificate() throws Exception {
        Mockito.when(certificateService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/certificate/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        Certificate cert = new Certificate();
        cert.setName("Certificado Test");
        Mockito.when(certificateService.findById(1L)).thenReturn(cert);

        mockMvc.perform(get("/api/v1/certificate/search/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindStudentsByIdCertificate() throws Exception {
        StudentByCertificateResponse response = StudentByCertificateResponse.builder()
                .name("Certificado Test")
                .studentDTOList(Collections.emptyList())
                .build();

        Mockito.when(certificateService.findStudentsByIdCertificate(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/certificate/search-student/1"))
                .andExpect(status().isOk());
    }
}