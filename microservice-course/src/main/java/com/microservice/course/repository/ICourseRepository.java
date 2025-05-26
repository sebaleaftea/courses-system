package com.microservice.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.course.model.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course , Long>{
    
}
