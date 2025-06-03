package com.microservice_support.controller;

import static org.mockito.Mockito.description;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice_support.dto.StudentDTO;
import com.microservice_support.http.response.StudentBySupportResponse;
import com.microservice_support.model.Support;
import com.microservice_support.service.ISupportService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api/v1/support")

public class SupportController {

    @Autowired
    private ISupportService supportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSupport(@RequestBody Support support){
        supportService.save(support);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllSupport(){
        return ResponseEntity.ok(supportService.findAll());
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(supportService.findById(id));
    }   
    
    @GetMapping("/search-student/{idSupport}")
    public ResponseEntity<?> findStudentsByIdCourse(@PathVariable Long idSupport){
        return ResponseEntity.ok(supportService.findStudentByIdSupport(idSupport));
    }
}
