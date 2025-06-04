package com.microservice_support.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name="supports")
@AllArgsConstructor
@NoArgsConstructor
public class Support {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String ticket; //se manejara como C-007,etc
    
    @Column(name = "status")
    private String status;  //pendiente, en proceso , resuelto


}
