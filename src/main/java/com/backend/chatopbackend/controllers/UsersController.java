package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}