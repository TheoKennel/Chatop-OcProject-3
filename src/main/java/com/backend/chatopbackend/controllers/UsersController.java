package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.services.UsersServices;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersServices usersServices;

    public Iterable<Users> getAllUsers() {
       return usersServices.getAllUsers();
    }

    @RequestMapping("/user")
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserId(@PathVariable("id") Integer id) {
        return usersServices.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping("/auth")
    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        Users registerUser = usersServices.registerUser(users);
        return ResponseEntity.ok(registerUser);
    }

    @RequestMapping("/auth")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        if (usersServices.loginUser(email, password)) {
          return ResponseEntity.ok(Map.of("token", "jwt"));
        }
       return ResponseEntity.notFound().build();
    }

    @RequestMapping("/auth")
    @GetMapping("/me")
    public ResponseEntity<Users> getMineUser() {
        return getUserId(1);
    }
}