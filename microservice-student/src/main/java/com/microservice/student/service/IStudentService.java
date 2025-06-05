package com.microservice.student.service;

import com.microservice.student.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    Student findById(Long id);
    
    void save(Student student);

    List<Student> findByIdCourse(Long idCourse);
    List<Student> findByIdEnrollment(Long idEnrollment);
    List<Student> findByIdCertificate(Long idCertificate);
    List<Student> findByIdSupport(Long idSupport);

}
