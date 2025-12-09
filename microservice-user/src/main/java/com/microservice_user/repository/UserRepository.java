package com.microservice_user.repository;

import com.microservice_user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Este es el método clave que necesita el servicio
    Optional<User> findByName(String name);
    
    Optional<User> findByEmail(String email);
}