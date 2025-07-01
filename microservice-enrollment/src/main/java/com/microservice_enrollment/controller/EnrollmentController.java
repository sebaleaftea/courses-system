package com.microservice_enrollment.controller;

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

import com.microservice_enrollment.model.Enrollment;
import com.microservice_enrollment.service.IEnrollmentService;

@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEnrollment(@RequestBody Enrollment enrollment){
        enrollmentService.save(enrollment);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllEnrollment(){
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.findById(id);
        EntityModel<Enrollment> resource = EntityModel.of(enrollment,
            linkTo(methodOn(EnrollmentController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(EnrollmentController.class).findAllEnrollment()).withRel("all-Enrollments")
        );
        return ResponseEntity.ok(resource);
    }    

    @GetMapping("/search-student/{idEnrollment}")
    public ResponseEntity<?> findStudentByIdEnrollment(@PathVariable Long idEnrollment){
        return ResponseEntity.ok(enrollmentService.findStudentsByIdEnrollment(idEnrollment));
    }
}
