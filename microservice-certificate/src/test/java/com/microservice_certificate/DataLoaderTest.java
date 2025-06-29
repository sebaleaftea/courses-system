package com.microservice_certificate;

import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DataLoaderTest {

    @Mock
    private ICertificateRepository iCertificateRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRunInsertsCertificates() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Ejecuta el método run
        dataLoader.run();

        // Verifica que se haya llamado al menos 1 vez al método save del repositorio
        verify(iCertificateRepository, atLeastOnce()).save(any(Certificate.class));
    }
}