package com.microservice.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.course.dto.StudentDTO;

import java.util.List;

@FeignClient(name = "msvc-student", url = "localhost:8090/api/v1/student")
public interface StudentClient {

    @GetMapping("/search-by-course/{id}")
    List<StudentDTO> findAllStudentByCourse(@PathVariable Long idCourse);

}
