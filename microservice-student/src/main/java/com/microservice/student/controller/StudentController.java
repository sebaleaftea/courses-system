package com.microservice.student.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.student.service.IStudentService;
import com.microservice.student.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Student student = iStudentService.findById(id);
        EntityModel<Student> resource = EntityModel.of(student,
            linkTo(methodOn(StudentController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(StudentController.class).findById(id)).withRel("get-student-by-id"),
            linkTo(methodOn(StudentController.class).findAllStudents()).withRel("all-students")
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (!id.equals(student.getId())) {
            return ResponseEntity.badRequest().build(); // El id de la URL y del cuerpo no coinciden
        }
        Student updated = iStudentService.updateStudent(student);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        iStudentService.deleteById(id);
        return ResponseEntity.noContent().build();
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
