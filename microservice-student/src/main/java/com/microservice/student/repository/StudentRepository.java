package com.microservice.student.repository;

import com.microservice.student.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    
    @Query("SELECT s FROM Student s WHERE s.courseId = :idCourse")
    List<Student> findAllStudent(Long idCourse);

    @Query("SELECT s FROM Student s WHERE s.enrollmentId = :idEnrollment")
    List<Student> findAllStudent(Long idEnrollment);

    @Query("SELECT s FROM Student s WHERE s.certificateId = :idCertificate")
    List<Student> findAllStudent(Long idCertificate);

    @Query("SELECT s FROM Student s WHERE s.supportId = :idSupport")
    List<Student> findAllStudent(Long idSupport);

    //Query Method
    //List<Student> findAllByCourseId(Long courseId);

}
