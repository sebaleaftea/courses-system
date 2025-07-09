package com.microservice.course.service;

import com.microservice.course.model.Course;
import com.microservice.course.http.response.StudentByCourseResponse;

import java.util.List;

public interface ICourseService {

    List<Course> findAll();

    Course findById(Long id);

    Course updateCourse(Course course);

    void save(Course course);

    void deleteById(Long id);

    StudentByCourseResponse findStudentsByIdCourse(Long idCourse);
}
