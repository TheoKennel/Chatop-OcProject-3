package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersServices usersServices;

    public Iterable<Users> getAllUsers() {
       return usersServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserId(@PathVariable("id") Integer id) {
        return usersServices.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        Users registerUser = usersServices.registerUser(users);
        return ResponseEntity.ok(registerUser);
    }

//    @PostMapping("/login")
}