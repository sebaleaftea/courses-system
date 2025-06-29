package com.microservice_enrollment;

import com.microservice_enrollment.model.Enrollment;
import com.microservice_enrollment.repository.IEnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DataLoaderTest {

    @Mock
    private IEnrollmentRepository enrollmentRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRunInsertsEnrollment() throws Exception {
        MockitoAnnotations.openMocks(this);

        dataLoader.run();

        verify(enrollmentRepository, atLeastOnce()).save(any(Enrollment.class));
    }


}
