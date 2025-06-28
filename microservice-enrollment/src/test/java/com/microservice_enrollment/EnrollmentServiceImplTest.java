package com.microservice_enrollment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.sql.Date;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice_enrollment.client.StudentClient;
import com.microservice_enrollment.dto.StudentDTO;
import com.microservice_enrollment.http.response.StudentByEnrollmentResponse;
import com.microservice_enrollment.controller.EnrollmentController;
import com.microservice_enrollment.model.Enrollment;
import com.microservice_enrollment.repository.IEnrollmentRepository;
import com.microservice_enrollment.service.EnrollmentServicelmpl;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceImplTest {

    @InjectMocks
    private EnrollmentServicelmpl enrollmentService;

    @Mock
    private IEnrollmentRepository enrollmentRepository;

    @Mock
    private StudentClient studentClient;

    @Test
    void testFindAll() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        when(enrollmentRepository.findAll()).thenReturn(List.of(enrollment));

        List<Enrollment> result = enrollmentService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("true", result.get(0).getPay());
        assertEquals("1 mes", result.get(0).getExpiration());
        assertEquals("curso iniciado", result.get(0).getStatus());

        /*
         * private Long id;
         * private String pay;
         * private String expiration; //1 mes, 2meses ,etc
         * private String status; //curso iniciado,pendiente,completado, etc
         */
    }

    @Test
    void testFindByIdEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        Enrollment foundEnrollment = enrollmentService.findById(1L);
        assertNotNull(foundEnrollment);
        assertEquals(1L, foundEnrollment.getId());
        assertEquals("true", foundEnrollment.getPay());
        assertEquals("1 mes", foundEnrollment.getExpiration());
        assertEquals("curso iniciado", foundEnrollment.getStatus());

    }

    @Test
    void testSaveEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        enrollmentService.save(enrollment);
        verify(enrollmentRepository, times(1)).save(enrollment);
    }  

    @Test
    void testFindStudentsByIdEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        // Assuming studentClient.findAllStudentByEnrollment returns an empty list for this test
        List<StudentDTO> studentDTOList = List.of(); // Mocked response

        StudentByEnrollmentResponse response = enrollmentService.findStudentsByIdEnrollment(1L);

        assertNotNull(response);
        assertEquals("true", response.getPay());
        assertEquals("1 mes", response.getExpiration());
        assertEquals("curso iniciado", response.getStatus());
        assertEquals(studentDTOList, response.getStudentDTOList());
    }  


}
