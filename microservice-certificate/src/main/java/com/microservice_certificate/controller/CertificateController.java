package com.microservice_certificate.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice_certificate.model.Certificate;
import com.microservice_certificate.service.ICertificateService;

@RestController
@RequestMapping("/api/v1/certificate")
public class CertificateController {

    @Autowired
    private ICertificateService certificateService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Certificate certificate){
        certificateService.save(certificate);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllCertificate(){
        return ResponseEntity.ok(certificateService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Certificate certificate = certificateService.findById(id);
        EntityModel<Certificate> resource = EntityModel.of(certificate,
            linkTo(methodOn(CertificateController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(CertificateController.class).findAllCertificate()).withRel("all-Certificates")
        );
        return ResponseEntity.ok(resource);
    }    


    @GetMapping("/search-student/{idCertificate}")
    public ResponseEntity<?> findStudentsByIdCertificate(@PathVariable Long idCertificate){
        return ResponseEntity.ok(certificateService.findStudentsByIdCertificate(idCertificate));
    }
}
