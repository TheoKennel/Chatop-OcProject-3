package com.backend.chatopbackend.repository;

import com.backend.chatopbackend.models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
