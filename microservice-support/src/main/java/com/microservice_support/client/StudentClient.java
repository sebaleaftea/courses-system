package com.microservice_support.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice_support.dto.StudentDTO;

@FeignClient(name = "msvc-student", url = "localhost:8090")
public interface StudentClient {

    
    @GetMapping("/api/v1/student/search-by-support/{supportId}")    
    List<StudentDTO> findAllStudentBySupport(@PathVariable Long supportId);

}