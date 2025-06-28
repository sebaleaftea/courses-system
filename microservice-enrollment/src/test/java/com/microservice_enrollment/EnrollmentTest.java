package com.microservice_enrollment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.microservice_enrollment.model.Enrollment;

public class EnrollmentTest {

    @Test
    void testGettersAndSetters() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setPay("true");
        enrollment.setExpiration("1 mes");
        enrollment.setStatus("curso iniciado");

        assertEquals(1L, enrollment.getId());
        assertEquals("true", enrollment.getPay());
        assertEquals("1 mes", enrollment.getExpiration());
        assertEquals("curso iniciado", enrollment.getStatus());
    }
    
    @Test
    void testBuilder(){
        Enrollment enrollment = Enrollment.builder()
                .id(2L)
                .pay("false")
                .expiration("2 meses")
                .status("curso finalizado")
                .build();

        assertEquals(2L, enrollment.getId());
        assertEquals("false", enrollment.getPay());
        assertEquals("2 meses", enrollment.getExpiration());
        assertEquals("curso finalizado", enrollment.getStatus());
    }

    @Test
    void testAllArgsConstructorAndNoArgsConstructor() {
        Enrollment enrollment1 = new Enrollment(3L, "true", "3 meses", "curso cancelado");
        assertEquals("true", enrollment1.getPay());

        Enrollment enrollment2 = new Enrollment();
        assertNull(enrollment2.getPay());
    }
}

