package com.example.projectedgecarftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    private String projectId;
    private String description;
    private String summery;
    private String gitHubLink;
    private String userType;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
