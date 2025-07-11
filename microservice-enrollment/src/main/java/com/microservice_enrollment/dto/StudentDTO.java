package com.microservice_enrollment.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    
    private String pay;
    private String expiration;
    private String status;
    private Long enrollmentId;
}
