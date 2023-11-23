package com.backend.chatopbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rental_id;
    private Integer user_id;
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
}
