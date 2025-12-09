package com.microservice_user.controller;

import com.microservice_user.model.User;
import com.microservice_user.service.UserService;

import jakarta.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173") 
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // CORRECCIÓN: Usamos findByName y getName() en lugar de email
        // Asegúrate de que tu UserService tenga el método findByName implementado
        User foundUser = userService.findByName(user.getName());
        
        if (foundUser != null && foundUser.getPasswordHash().equals(user.getPasswordHash())) {
            return ResponseEntity.ok(foundUser);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

        @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}