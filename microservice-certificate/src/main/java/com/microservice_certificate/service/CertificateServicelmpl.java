package com.microservice_certificate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;

public class CertificateServicelmpl implements ICertificateService{

    @Autowired
    private ICertificateRepository iCertificateRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Certificate> findAll(){
        return (List<Certificate>) iCertificateRepository.findAll();
    }

    @Override
    public Certificate findById(Long id){
        return iCertificateRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Certificate certificate){
        iCertificateRepository.save(certificate);
    }

    //instanciar el response de estudiante a travez del http>response...


}
