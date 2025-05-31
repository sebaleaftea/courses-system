package com.microservice_certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice_certificate.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
