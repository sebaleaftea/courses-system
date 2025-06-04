package com.microservice_enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice_enrollment.model.Enrollment;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long>{

}
