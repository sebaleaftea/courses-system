package com.microservice_support;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice_support.controller.SupportController;
import com.microservice_support.http.response.StudentBySupportResponse;
import com.microservice_support.model.Support;
import com.microservice_support.service.ISupportService;

@WebMvcTest(SupportController.class)
public class SupportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISupportService supportService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveSupport() throws Exception {
        Support supp = new Support();
        supp.setTicket("Ticket Soporte");
        supp.setStatus("Pendiente");

        mockMvc.perform(post("/api/v1/support/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supp)))
                .andExpect(status().isCreated());
    }

    @Test
    void testFindAllSupport() throws Exception {
        Support supp = new Support();
        supp.setId(1L);
        supp.setTicket("Ticket Soporte");
        supp.setStatus("Pendiente");

        when(supportService.findAll()).thenReturn(List.of(supp));

        mockMvc.perform(get("/api/v1/support/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].ticket").value("Ticket Soporte"));
    }

    @Test
    void testFindById() throws Exception {
        Support supp = new Support();
        supp.setId(2L);
        supp.setTicket("Otro Ticket");
        supp.setStatus("Resuelto");

        when(supportService.findById(2L)).thenReturn(supp);

        mockMvc.perform(get("/api/v1/support/search/id/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.ticket").value("Otro Ticket"));
    }

    @Test
    void testFindStudentByIdSupport() throws Exception {
        StudentBySupportResponse response = StudentBySupportResponse.builder()
                .ticket("Ticket Soporte")
                .status("Pendiente")
                .studentDTOList(Collections.emptyList())
                .build();

        when(supportService.findStudentByIdSupport(3L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/support/search-student/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket").value("Ticket Soporte"))
                .andExpect(jsonPath("$.status").value("Pendiente"))
                .andExpect(jsonPath("$.studentDTOList").isArray());
     
    }
}
