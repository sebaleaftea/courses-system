package com.microservice.student.controller;

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
    @GetMapping("/search-by-course/{courseId}")
    public ResponseEntity<?> findByIdCourse(@PathVariable Long courseId){
         System.out.println("-------------------------------------------------------------------"+ courseId);
         return ResponseEntity.ok(iStudentService.findByIdCourse(courseId));
    }

    @GetMapping("/search-by-enrollment/{enrollmentId}")
    public ResponseEntity<?> findByIdEnrollment(@PathVariable Long enrollmentId){
         System.out.println("-------------------------------------------------------------------"+ enrollmentId);
         return ResponseEntity.ok(iStudentService.findByIdEnrollment(enrollmentId));
    }

    @GetMapping("/search-by-certificate/{certificateId}")
    public ResponseEntity<?> findByIdCertificate(@PathVariable Long certificateId){
         System.out.println("-------------------------------------------------------------------"+ certificateId);
         return ResponseEntity.ok(iStudentService.findByIdCertificate(certificateId));
    }

    @GetMapping("/search-by-support/{supportId}")
    public ResponseEntity<?> findByIdSupport(@PathVariable Long supportId){
         System.out.println("-------------------------------------------------------------------"+ supportId);
         return ResponseEntity.ok(iStudentService.findByIdSupport(supportId));
    }

}
