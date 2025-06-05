package com.microservice_certificate.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private String name;
    private Date issueDate;
    private Date expirationDate;
    private Long certificateId;
}
