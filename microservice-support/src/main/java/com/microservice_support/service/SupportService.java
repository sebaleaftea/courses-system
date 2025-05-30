package com.microservice_support.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice_support.model.Support;
import com.microservice_support.repository.SupportRepository;

@Service
public class SupportService {

    @Autowired
    private SupportRepository supportRepository;

    public List<Support> findAll(){
        return (List<Support>) supportRepository.findAll();
    }

    
    public Support findById(Long id){
        return supportRepository.findById(id).orElseThrow();
    }

  
    public void save(Support support){
        supportRepository.save(support);
    }

   
    public void delete(Long id){
        supportRepository.deleteById(id);
    }


    public Object findStudentsByIdSupport(Long idSupport) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStudentsByIdSupport'");
    }

    /* 
    public List<Support> findStudentsByIdSupport(Long idSupport) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStudentsByIdSupport'");
    }
        por revisar....
     */ 

}
