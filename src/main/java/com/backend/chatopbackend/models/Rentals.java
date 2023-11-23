package com.backend.chatopbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String picture;
    private String description;
    @Column(nullable = false)
    private Integer owner_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private Users owner;
}
