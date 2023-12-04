package com.backend.chatopbackend.repository;

import com.backend.chatopbackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDetailsRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
