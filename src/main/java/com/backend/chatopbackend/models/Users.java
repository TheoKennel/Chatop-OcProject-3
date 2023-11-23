package com.backend.chatopbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    private void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }
//
//    @OneToMany(mappedBy = "user_id")
//    private List<Messages> messages;
//
//    @OneToMany(mappedBy = "owner")
//    private List<Rentals> rentals;
}
