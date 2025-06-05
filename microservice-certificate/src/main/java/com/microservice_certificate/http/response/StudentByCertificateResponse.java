package com.microservice_certificate.http.response;

import java.sql.Date;
import java.util.List;

import com.microservice_certificate.dto.StudentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByCertificateResponse {

    private String name;
    private Date issueDate;
    private Date expirationDate;
    private List<StudentDTO> studentDTOList;
}
