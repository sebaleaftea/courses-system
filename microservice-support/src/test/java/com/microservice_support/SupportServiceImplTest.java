package com.microservice_support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice_support.client.StudentClient;
import com.microservice_support.dto.StudentDTO;
import com.microservice_support.http.response.StudentBySupportResponse;
import com.microservice_support.model.Support;
import com.microservice_support.repository.ISupportRepository;
import com.microservice_support.service.SupportServicelmpl;

@ExtendWith(MockitoExtension.class)
public class SupportServiceImplTest {
    
    @InjectMocks
    private SupportServicelmpl supportService;

    @Mock
    private ISupportRepository iSupportRepository;

    @Mock
    private StudentClient studentClient;

    @Test
    void testFindAll(){
        Support supp = new Support();
        supp.setId(1L);
        supp.setTicket("Ticket de falla de pago");
        supp.setStatus("Pendiente");

        when(iSupportRepository.findAll()).thenReturn(List.of(supp));

        List<Support> result = supportService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Ticket de falla de pago", result.get(0).getTicket());
        assertEquals("Pendiente", result.get(0).getStatus());

    }

    @Test
    void testFindById(){
        Support supp = new Support();
        supp.setId(1L);
        supp.setTicket("Ticket de falla de pago");
        supp.setStatus("Pendiente");

        when(iSupportRepository.findById(1L)).thenReturn(Optional.of(supp));

        Support found = supportService.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Ticket de falla de pago", found.getTicket());
        assertEquals("Pendiente",found.getStatus());
    }

    @Test
    void testSave() {
        Support supp = new Support();
        supp.setId(1L);
        supp.setTicket("Ticket de falla de pago");
        supp.setStatus("Pendiente");

        supportService.save(supp);

        verify(iSupportRepository, times(1)).save(supp);
    }
    
    @Test
    void testFindStudentsBySupport(){
        Support supp = new Support();
        supp.setId(1L);
        supp.setTicket("Ticket de falla de pago");
        supp.setStatus("Pendiente");

        StudentDTO student = StudentDTO.builder()
            .ticket("Ticket de proceso")
            .status("Finalizado")
            .supportId(1L)
            .build();
        
        when(iSupportRepository.findById(1L)).thenReturn(Optional.of(supp));
        when(studentClient.findAllStudentBySupport(1L)).thenReturn(List.of(student));
        
        StudentBySupportResponse response = supportService.findStudentByIdSupport(1L);

        assertNotNull(response);
        assertEquals("Ticket de falla de pago", response.getTicket());
        assertEquals(1, response.getStudentDTOList().size());
        assertEquals("Finalizado", response.getStudentDTOList().get(0).getStatus());
    }

}
