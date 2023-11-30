package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.services.JWTService;
import com.backend.chatopbackend.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UsersAuthController {

    @Autowired
    private UsersServices usersServices;
    @Autowired
    private JWTService jwtService;

    public Iterable<Users> getAllUsers() {
       return usersServices.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        Users registerUser = usersServices.registerUser(users);
        String token = jwtService.generateToken(users.getEmail());
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
            if (usersServices.loginUser(email, password)) {
            String token = jwtService.getToken(email);
          return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    @GetMapping("/me")
    public Optional<Users> getMineUser() {
        return usersServices.getUserById(1);
    }
}