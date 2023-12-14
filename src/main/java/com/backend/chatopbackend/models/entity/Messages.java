package com.backend.chatopbackend.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="messages")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    @JsonProperty(value = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rentals rentals;
}
