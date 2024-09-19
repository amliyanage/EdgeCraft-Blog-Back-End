package com.example.projectedgecarftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(nullable = false , unique = true)
    private String userName;

    @Column(nullable = false , unique = true)
    private String email;

    private String password;
    private String role;
    private String status;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;
}
