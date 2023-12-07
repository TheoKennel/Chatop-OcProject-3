package com.backend.chatopbackend.controllers;

import com.backend.chatopbackend.configuration.jwt.JwtTokenProvider;
import com.backend.chatopbackend.dto.LoginRequest;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users) {
        String userPassword = users.getPassword();
        Users user =  usersServices.registerUser(users);
        Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       user.getEmail(),
                       userPassword
               )
       );
        String token = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String userEmail = loginRequest.getEmail();
        String userPassword = loginRequest.getPassword();
        Authentication authenticate = usersServices.loginUser(userEmail, userPassword);
        if (authenticate != null) {
            String token = jwtTokenProvider.createToken(authenticate);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    @GetMapping("/me")
    public Users getLoggedUser() {
      return usersServices.getConnectedUser();
    }
}