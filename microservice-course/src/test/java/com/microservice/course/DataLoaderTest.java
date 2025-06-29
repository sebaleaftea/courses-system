package com.microservice.course;

import com.microservice.course.model.Course;
import com.microservice.course.repository.ICourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DataLoaderTest {

    @Mock
    private ICourseRepository iCourseRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void testRunInsertsCourses() throws Exception {
        MockitoAnnotations.openMocks(this);

        dataLoader.run();

        verify(iCourseRepository, atLeastOnce()).save(any(Course.class));
    }
}
