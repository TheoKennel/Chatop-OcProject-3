package com.backend.chatopbackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Users user;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    @ToString.Exclude
    private Rentals rentals;

}
