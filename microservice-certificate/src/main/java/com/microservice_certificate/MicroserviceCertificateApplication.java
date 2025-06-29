
package com.microservice_certificate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceCertificateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCertificateApplication.class, args);
	}


}
