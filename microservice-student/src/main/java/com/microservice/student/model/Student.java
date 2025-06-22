package com.microservice.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name="students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String email;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "certificate_Id")
    private Long certificateId;

    @Column(name = "Enrollment_Id")
    private Long enrollmentId;
  
    @Column(name = "Support_Id")
    private Long supportId;

    

}
