package com.microservice_support.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {


    private String ticket;
    private String status;
    private Long supportId;
}

