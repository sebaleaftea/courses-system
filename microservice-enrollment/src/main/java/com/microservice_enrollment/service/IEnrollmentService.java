package com.microservice_enrollment.service;

import java.util.List;

import com.microservice_enrollment.model.Enrollment;

public interface IEnrollmentService {

    List<Enrollment> findAll();

    Enrollment findById(Long id);

    void save(Enrollment enrollment);

    StudentByEnrollmentResponse findStudentsByIdEnrollment(Long idEnrollment);

}
