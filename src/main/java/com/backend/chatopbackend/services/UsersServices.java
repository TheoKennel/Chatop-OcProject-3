package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Users registerUser(Users users) {
        String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        usersRepository.save(users);
        return users;
    }

    public Authentication loginUser(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            System.out.println(authentication);
            return authentication;
        } catch (Exception e) {
            System.out.println("Cant authenticate : " + e);
            return null;
        }
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getConnectedUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return usersRepository.findByEmail(auth.getName()).orElse(null);
        } catch (Exception e) {
            System.out.println("Error while retrieving user : " + e);
        }
        return null;
     }
}
