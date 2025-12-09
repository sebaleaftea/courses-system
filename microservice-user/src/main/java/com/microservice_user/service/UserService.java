package com.microservice_user.service;

import com.microservice_user.model.User;
import com.microservice_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    // Método necesario para buscar por nombre
    public User findByName(String name) {
        // Asumiendo que tu repositorio devuelve Optional<User>
        return userRepository.findByName(name).orElse(null);
    }

    // Lógica de Login actualizada
    public User login(String name, String password) {
        // 1. Buscamos al usuario por nombre
        Optional<User> userOpt = userRepository.findByName(name);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 2. Comparamos la contraseña (texto plano por ahora)
            if (user.getPasswordHash().equals(password)) {
                return user; // Login exitoso
            }
        }
        return null; 
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}