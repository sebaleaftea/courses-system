package com.microservice.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.student.service.IStudentService;
import com.microservice.student.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping("/api/v1/student")
public class StudentController {


    @Autowired
    private IStudentService iStudentService;

    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student){
        iStudentService.save(student);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllStudents(){
        return ResponseEntity.ok(iStudentService.findAll());
    }
    

    @GetMapping("/search/{id}")    
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(iStudentService.findById(id));
    }

    //localhost:8090/api/v1/student/search-by-course/1
    @GetMapping("/search-certificate/{idCertificate}")
    public ResponseEntity<List<Student>> findByIdCertificate(@PathVariable Long idCertificate) {
        return ResponseEntity.ok(iStudentService.findByIdCertificate(idCertificate));
    }

    @GetMapping("/search-course/{idCourse}")
    public ResponseEntity<List<Student>> findByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(iStudentService.findByIdCourse(idCourse));
    }

    @GetMapping("/search-enrollment/{idEnrollment}")
    public ResponseEntity<List<Student>> findByIdEnrollment(@PathVariable Long idEnrollment) {
        return ResponseEntity.ok(iStudentService.findByIdEnrollment(idEnrollment));
    }

    @GetMapping("/search-support/{idSupport}")
    public ResponseEntity<List<Student>> findByIdSupport(@PathVariable Long idSupport) {
        return ResponseEntity.ok(iStudentService.findByIdSupport(idSupport));
    }

}
