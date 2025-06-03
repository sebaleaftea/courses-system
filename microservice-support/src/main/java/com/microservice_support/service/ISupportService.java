package com.microservice_support.service;

import java.util.List;

import com.microservice_support.http.response.StudentBySupportResponse;
import com.microservice_support.model.Support;

public interface ISupportService {

    List<Support> findAll();

    Support findById(Long id);

    void save(Support support);

    StudentBySupportResponse findStudentByIdSupport(Long idSupport);

}
