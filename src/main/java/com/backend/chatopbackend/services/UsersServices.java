package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.repository.UsersRepository;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Users registerUser(Users users) {
        String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        usersRepository.save(users);
        return users;
    }

    public Boolean loginUser(String email, String password) {
       return usersRepository.findByEmail(email)
               .map(users -> bCryptPasswordEncoder.matches(password, users.getPassword()))
                .orElse(false);
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
