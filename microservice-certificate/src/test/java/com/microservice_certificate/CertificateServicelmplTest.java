package com.microservice_certificate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.microservice_certificate.client.StudentClient;
import com.microservice_certificate.dto.StudentDTO;
import com.microservice_certificate.http.response.StudentByCertificateResponse;
import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;
import com.microservice_certificate.service.CertificateServicelmpl;

public class CertificateServicelmplTest {

    @Mock
    private ICertificateRepository iCertificateRepository;

    @Mock
    private StudentClient studentClient;

    @InjectMocks
    private CertificateServicelmpl certificateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdReturnsCertificate() {
        Certificate cert = new Certificate();
        cert.setName("Test Cert");
        when(iCertificateRepository.findById(1L)).thenReturn(Optional.of(cert));

        Certificate result = certificateService.findById(1L);

        assertNotNull(result);
        assertEquals("Test Cert", result.getName());
        verify(iCertificateRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveCallsRepository() {
        Certificate cert = new Certificate();
        certificateService.save(cert);
        verify(iCertificateRepository, times(1)).save(cert);
    }

    @Test
    void testFindStudentsByIdCertificate() {
        // Arrange
        Certificate cert = new Certificate();
        cert.setName("Certificado Java");
        cert.setIssueDate(Date.valueOf("2024-01-01"));
        cert.setExpirationDate(Date.valueOf("2025-01-01"));

        StudentDTO student = StudentDTO.builder()
                .name("Juan")
                .issueDate(Date.valueOf("2024-01-01"))
                .expirationDate(Date.valueOf("2025-01-01"))
                .certificateId(1L)
                .build();

        when(iCertificateRepository.findById(1L)).thenReturn(Optional.of(cert));
        when(studentClient.findAllStudentByCertificate(1L)).thenReturn(Arrays.asList(student));

        StudentByCertificateResponse response = certificateService.findStudentsByIdCertificate(1L);

        assertNotNull(response);
        assertEquals("Certificado Java", response.getName());
        assertEquals(1, response.getStudentDTOList().size());
        assertEquals("Juan", response.getStudentDTOList().get(0).getName());
        verify(iCertificateRepository, times(1)).findById(1L);
        verify(studentClient, times(1)).findAllStudentByCertificate(1L);
    }
}
