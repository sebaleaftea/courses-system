package com.microservice_certificate.service;

import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;

import com.microservice_certificate.repository.ICertificateRepository;
import com.netflix.discovery.converters.Auto;

@SpringBootTest
@ActiveProfiles("test")
public class CertificateServiceTest {

    @Autowired
    private ICertificateService certificateService;

    @MockBean
    private ICertificateRepository certificateRepository;

    @Test
    public void testFindAllCertificates() {
        
        when(certificateRepository.findAll()).thenReturn(List.of(new Certificate("123", "Test Certificate")));

        List<Certificate> certificates = certificateService.findAllCertificates();
        assertNotNull(certificates);
        assertEquals(1, certificates.size());
        assertEquals("123", certificates.get(0).getId());
        assertEquals("Test Certificate", certificates.get(0).getName());

    }


}
