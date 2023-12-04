package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.configuration.jwt.JwtTokenProvider;
import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class UsersAuthController {

    @Autowired
    private UsersServices usersServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public Iterable<Users> getAllUsers() {
       return usersServices.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users) {
       Users user =  usersServices.registerUser(users);
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       user.getEmail(),
                       user.getPassword()
               )
       );
        String token = jwtTokenProvider.createToken(authentication);
//        String token = jwtService.generateToken(users.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
            if (authentication.isAuthenticated()) {
            String token = jwtTokenProvider.createToken(authentication);
          return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestParam AuthenticationRequest authenticationRequest) {
//        Authentication authentication = authenticationManager.authenticate(
////                authenticationRequest.get, password
//        )
//    }
    @GetMapping("/me")
    public Optional<Users> getMineUser() {
        return usersServices.getUserById(1);
    }
}