package com.microservice_certificate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice_certificate.client.StudentClient;
import com.microservice_certificate.dto.StudentDTO;
import com.microservice_certificate.http.response.StudentByCertificateResponse;
import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.repository.ICertificateRepository;

@Service
public class CertificateServiceImpl implements ICertificateService{

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

    @Override
    public StudentByCertificateResponse findStudentsByIdCertificate(Long idCertificate){

        Certificate certificate = iCertificateRepository.findById(idCertificate).orElse(new Certificate());

        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCertificate(idCertificate);

        return StudentByCertificateResponse.builder()
                .name(certificate.getName())
                .expirationDate(certificate.getExpirationDate())
                .issueDate(certificate.getIssueDate())
                .studentDTOList(studentDTOList)
                .build();     
    }

}
