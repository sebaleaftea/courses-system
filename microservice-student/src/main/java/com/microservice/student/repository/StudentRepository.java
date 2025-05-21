package com.microservice.student.repository;

import com.microservice.student.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    
    @Query("SELECT s FROM Student s WHERE s.courseId = :idCouse")
    List<Student> findAllStudent(Long idCourse);

    //Query Method
    //List<Student> findAllByCourseId(Long courseId);

}
