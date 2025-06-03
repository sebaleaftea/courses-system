package com.microservice_support.http.response;

import com.microservice_support.dto.StudentDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentBySupportResponse {

    private String ticket;
    private String status;
    private List<StudentDTO> studentDTOList;

}