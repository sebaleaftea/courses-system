package com.microservice.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;

import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.model.Course;
import com.microservice.course.repository.ICourseRepository;
import com.microservice.course.service.CourseServiceImpl;


@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {
    
    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private StudentClient studentClient;

    @Test
    void testFindAll() {

        Course course = new Course();
        course.setId(1L);
        course.setName("Course Test");
        course.setTeacher("John Doe");

        when(courseRepository.findAll()).thenReturn(List.of(course));

        List<Course> result = courseService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Course Test", result.get(0).getName());
        assertEquals("John Doe", result.get(0).getTeacher());
    }

    @Test
    void testFindById() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course Test");
        course.setTeacher("John Doe");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseService.findById(1L);
        assertNotNull(result);
        assertEquals("Course Test", result.getName());
        assertEquals("John Doe", result.getTeacher());
    }

    @Test  
    void testSave() {
        Course course = new Course();
        course.setName("Course Test");
        course.setTeacher("John Doe");

        courseService.save(course);

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testFindStudentsByIdCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course Test");
        course.setTeacher("John Doe");

        StudentDTO student = StudentDTO.builder()
                .name("Juan")
                .lastName("Perez")
                .email("juan.perez@example.com")
                .courseId(1L)
                .build();
    
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(studentClient.findAllStudentByCourse(1L)).thenReturn(List.of(student));

        StudentByCourseResponse response = courseService.findStudentsByIdCourse(1L);

        assertNotNull(response);
        assertEquals("Course Test", response.getCourseName());
        assertEquals("John Doe", response.getTeacher());
        assertNotNull(response.getStudentDTOList());
        assertEquals(1, response.getStudentDTOList().size());
        assertEquals("Juan", response.getStudentDTOList().get(0).getName());
        assertEquals("Perez", response.getStudentDTOList().get(0).getLastName());
        assertEquals("juan.perez@example.com", response.getStudentDTOList().get(0).getEmail());
        assertEquals(1L, response.getStudentDTOList().get(0).getCourseId());
    }

}
