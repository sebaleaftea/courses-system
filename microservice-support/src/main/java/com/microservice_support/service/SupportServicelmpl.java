package com.microservice_support.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice_support.client.StudentClient;
import com.microservice_support.dto.StudentDTO;
import com.microservice_support.http.response.StudentBySupportResponse;
import com.microservice_support.model.Support;
import com.microservice_support.repository.ISupportRepository;

@Service
public class SupportServicelmpl implements ISupportService{


    @Autowired
    private ISupportRepository iSupportRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Support> findAll(){
        return (List<Support>) iSupportRepository.findAll();
    }

    @Override
    public Support findById(Long id){
        return iSupportRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Support support){
        iSupportRepository.save(support);
    }

    @Override
    public StudentBySupportResponse findStudentByIdSupport(Long idSupport){

        Support support = iSupportRepository.findById(idSupport).orElse(new Support());

        List<StudentDTO> studentDTOList = studentClient.findAllStudentBySupport(idSupport);
    
        return StudentBySupportResponse.builder()
                .ticket(support.getTicket())
                .status(support.getStatus())
                .studentDTOList(studentDTOList)
                .build();
    }
}
