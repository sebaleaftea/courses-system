package com.microservice_enrollment.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-student", url = "localhost:8090")
public class StudentClient {

    

}
