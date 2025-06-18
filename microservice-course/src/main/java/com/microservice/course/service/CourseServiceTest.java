package com.microservice.course.service;

import com.microservice.course.model.Course;
import com.microservice.course.repository.ICourseRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class CourseServiceTest {
    
    @Autowired
    private CourseService courseService;

    @MockBean
    private ICourseRepository courseRepository;

    @Test
    public void testFindAllCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(new Course("Course 1", "John Doe")));

        List<Course> courses = courseService.findAllCourses();
        assertNotNull(courses);
        assertEquals(1, courses.size());
        assertEquals("Course 1", courses.get(0).getName());
        assertEquals("John Doe", courses.get(0).getTeacher());
    }
}
