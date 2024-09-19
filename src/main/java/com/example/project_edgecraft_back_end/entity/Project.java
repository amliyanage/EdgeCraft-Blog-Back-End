package com.example.project_edgecraft_back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "projects")
@Entity
public class Project {

    @Id
    private String projectId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String summery;

    @Column(nullable = false)
    private String githubLink;

    @Column(nullable = false)
    private String userType;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;
}
