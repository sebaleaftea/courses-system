package com.microservice.course.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.microservice.course.model.Course;
import com.microservice.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Course course) {                
        courseService.save(course);
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> findAllCourse(){
        return ResponseEntity.ok(courseService.findAll());
    }
    
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Course course = courseService.findById(id);
        EntityModel<Course> resource = EntityModel.of(course,
            linkTo(methodOn(CourseController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(CourseController.class).findAllCourse()).withRel("all-courses")
        );
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        
        if (!id.equals(course.getId())) {
            return ResponseEntity.badRequest().build(); // El id de la URL y del cuerpo no coinciden
        }
        Course updated = courseService.updateCourse(course);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search-student/{idCourse}")
    public ResponseEntity<?> findStudentsByIdCourse(@PathVariable Long idCourse){
        return ResponseEntity.ok(courseService.findStudentsByIdCourse(idCourse));
    }

}
