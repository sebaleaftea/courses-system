package com.microservice_support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSupportApplication.class, args);
	}

}
