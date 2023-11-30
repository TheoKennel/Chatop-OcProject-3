package com.backend.chatopbackend.services;

import com.backend.chatopbackend.models.Users;
import com.backend.chatopbackend.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
        return usersRepository.save(users);
    }


    public Users authenticateUser(String email, String password) {
        Optional<Users> user = usersRepository.findByEmail(email);
        user.ifPresent(users -> bCryptPasswordEncoder.matches(password, users.getPassword()));
        return user.get();
    }

    public Boolean loginUser(String email, String password) {
       return usersRepository.findByEmail(email).map( users ->
                 users.getPassword().equals(password))
                .orElse(false);
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
