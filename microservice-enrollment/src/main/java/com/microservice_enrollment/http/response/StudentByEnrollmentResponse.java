package com.microservice_enrollment.http.response;

import java.util.List;

import com.microservice_enrollment.dto.StudentDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StudentByEnrollmentResponse {
    private String pay;
    private String expiration;
    private String status;
    private List<StudentDTO> studentDTOList;

}
