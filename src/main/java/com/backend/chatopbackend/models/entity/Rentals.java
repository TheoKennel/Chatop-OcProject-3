package com.backend.chatopbackend.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="rentals")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String picture;
    @Column(length = 5000)
    private String description;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner;
}