package com.microservice_certificate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;

@Service
public interface ICertificateService {

   List<Certificate> findAll();

   Certificate findById(Long id);

   void save(Certificate certificate);

   StudentByCertificateResponse findStudentsByIdCertificate(Long idCertificate);



}
