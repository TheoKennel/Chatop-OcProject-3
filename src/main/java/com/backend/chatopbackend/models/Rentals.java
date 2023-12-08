package com.backend.chatopbackend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@Entity
//@Data
//public class Rentals {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String name;
//    private BigDecimal surface;
//    private BigDecimal price;
//    private String picture;
//    private String description;
//    private LocalDateTime created_at;
//    private LocalDateTime updated_at;
//
//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private Users owner;
//
//    @OneToMany(mappedBy = "rentals")
//    private List<Messages> messages;
//
//    @Override
//    public String toString() {
//        return "Rentals{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", surface=" + surface +
//                ", price=" + price +
//                ", picture='" + picture + '\'' +
//                ", description='" + description + '\'' +
//                ", created_at=" + created_at +
//                ", updated_at=" + updated_at +
//                '}';
//    }
//}

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
    private String description;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner;
}