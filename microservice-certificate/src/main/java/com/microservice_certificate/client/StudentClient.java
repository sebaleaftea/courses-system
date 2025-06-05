package com.microservice_certificate.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice_certificate.dto.StudentDTO;

@FeignClient(name = "msvc-student", url = "localhost:8090")
public interface StudentClient {

    @GetMapping("/api/v1/student/search-by-certificate/{certificateId}")
    List<StudentDTO> findAllStudentByCertificate(@PathVariable Long certificateId);
}
