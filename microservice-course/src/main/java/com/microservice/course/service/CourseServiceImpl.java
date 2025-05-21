package com.microservice.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.course.model.Course;
import com.microservice.course.repository.ICourseRepository;

public class CourseServiceImpl implements ICourseService{

    @Autowired
    private ICourseRepository iCourseRepository;

    @Override
    public List<Course> findAll() {
        return (List<Course>) iCourseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return iCourseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        iCourseRepository.save(course);
    }

}
