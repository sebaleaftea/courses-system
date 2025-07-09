package com.microservice.student.service;

import com.microservice.student.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();

    Student findById(Long id);

    Student updateStudent(Student student);
    
    void save(Student student);

    void deleteById(Long id);

    List<Student> findByIdCourse(Long idCourse);
    List<Student> findByIdEnrollment(Long idEnrollment);
    List<Student> findByIdCertificate(Long idCertificate);
    List<Student> findByIdSupport(Long idSupport);

}
