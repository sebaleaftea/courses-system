package com.microservice_support;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.microservice_support.repository.ISupportRepository;


class DataLoaderTest {

    @Mock
    private ISupportRepository supportRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRunInsertsSupportsWhenEmpty() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(supportRepository.count()).thenReturn(0L);

        dataLoader.run();

        verify(supportRepository, atLeastOnce()).save(any());
    }

    @Test
    void testRunDoesNothingWhenNotEmpty() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(supportRepository.count()).thenReturn(10L);

        dataLoader.run();

        verify(supportRepository, never()).save(any());
    }
}
