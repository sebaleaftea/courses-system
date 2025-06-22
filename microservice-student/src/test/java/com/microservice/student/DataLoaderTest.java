package com.microservice.student;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.microservice.student.repository.StudentRepository;

public class DataLoaderTest {
    
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRunInsertsStudentsWhenEmpty() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(studentRepository.count()).thenReturn(0L);

        dataLoader.run();

        verify(studentRepository, atLeastOnce()).save(any());
    }

    @Test
    void testRunDoesNothingWhenNotEmpty() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(studentRepository.count()).thenReturn(10L);

        dataLoader.run();

        verify(studentRepository, never()).save(any());
    }
}
