package com.microservice_certificate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.sql.Date;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice_certificate.client.StudentClient;
import com.microservice_certificate.dto.StudentDTO;
import com.microservice_certificate.http.response.StudentByCertificateResponse;
import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;
import com.microservice_certificate.service.CertificateServiceImpl;



@ExtendWith(MockitoExtension.class)
class CertificateServiceImplTest {

    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Mock
    private ICertificateRepository certificateRepository;

    @Mock
    private StudentClient studentClient;

    @Test
    void testFindAll() {
        Certificate cert = new Certificate();
        cert.setId(1L);
        cert.setName("Certificado Test");
        cert.setIssueDate(Date.valueOf("2024-01-01"));
        cert.setExpirationDate(Date.valueOf("2025-01-01"));

        when(certificateRepository.findAll()).thenReturn(List.of(cert));

        List<Certificate> result = certificateService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Certificado Test", result.get(0).getName());
        assertEquals(Date.valueOf("2024-01-01"), result.get(0).getIssueDate());
        assertEquals(Date.valueOf("2025-01-01"), result.get(0).getExpirationDate());
    }

    @Test
    void testFindById() {
        Certificate cert = new Certificate();
        cert.setId(1L);
        cert.setName("Certificado Test");
        cert.setIssueDate(Date.valueOf("2024-01-01"));
        cert.setExpirationDate(Date.valueOf("2025-01-01"));

        when(certificateRepository.findById(1L)).thenReturn(Optional.of(cert));

        Certificate found = certificateService.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Certificado Test", found.getName());
        assertEquals(Date.valueOf("2024-01-01"), found.getIssueDate());
        assertEquals(Date.valueOf("2025-01-01"), found.getExpirationDate());
    }

    @Test
    void testSave() {
        Certificate cert = new Certificate();
        cert.setName("Nuevo Certificado");
        cert.setIssueDate(Date.valueOf("2024-01-01"));
        cert.setExpirationDate(Date.valueOf("2025-01-01"));

        certificateService.save(cert);

        verify(certificateRepository, times(1)).save(cert);
    }

    @Test
    void testFindStudentsByIdCertificate() {
        Certificate cert = new Certificate();
        cert.setId(1L);
        cert.setName("Certificado Java");
        cert.setIssueDate(Date.valueOf("2024-01-01"));
        cert.setExpirationDate(Date.valueOf("2025-01-01"));

        StudentDTO student = StudentDTO.builder()
                .name("Juan")
                .certificateId(1L)
                .issueDate(Date.valueOf("2024-01-01"))
                .expirationDate(Date.valueOf("2025-01-01"))
                .build();

        when(certificateRepository.findById(1L)).thenReturn(Optional.of(cert));
        when(studentClient.findAllStudentByCertificate(1L)).thenReturn(List.of(student));

        StudentByCertificateResponse response = certificateService.findStudentsByIdCertificate(1L);

        assertNotNull(response);
        assertEquals("Certificado Java", response.getName());
        assertEquals(1, response.getStudentDTOList().size());
        assertEquals("Juan", response.getStudentDTOList().get(0).getName());
    }
}

