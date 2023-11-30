package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.services.JWTService;
import com.backend.chatopbackend.services.UsersServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UsersAuthController {

    private UsersServices usersServices;
    private JWTService jwtService;

    public Iterable<Users> getAllUsers() {
       return usersServices.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        Users registerUser = usersServices.registerUser(users);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(Authentication authentication, @RequestParam String email, @RequestParam String password) {
        String token = jwtService.getToken(authentication);
        if (usersServices.loginUser(email, password)) {
//          return ResponseEntity.ok(Map.of("token", "jwt"), token);
            return ResponseEntity.ok(token);
        }
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    public Optional<Users> getMineUser() {
        return usersServices.getUserById(1);
    }
}