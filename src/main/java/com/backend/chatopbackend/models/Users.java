package com.backend.chatopbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

//@Data
//@Entity
//public class Users {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(unique = true)
//    private String email;
//    private String name;
//    private String password;
//    private LocalDateTime created_at;
//    private LocalDateTime updated_at;
//
//    @PrePersist
//    private void onCreate() {
//        this.created_at = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    private void onUpdate() {
//        this.updated_at = LocalDateTime.now();
//    }
//
//    @OneToMany(mappedBy = "user")
//    private List<Messages> messages;
//
//    @OneToMany(mappedBy = "owner")
//    private List<Rentals> rentals;
//
//    @Override
//    public String toString() {
//        return "Users{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", created_at=" + created_at +
//                ", updated_at=" + updated_at +
//                '}';
//    }
//}
@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonProperty(value = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
