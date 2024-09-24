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
    private String projectTitle;
    private String projectType;
    private String projectStatus;
    private String description;
    private String summery;
    private String gitHubLink;
    private String date;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
