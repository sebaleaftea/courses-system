package com.microservice_user.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 60)
    private String passwordHash;

}
