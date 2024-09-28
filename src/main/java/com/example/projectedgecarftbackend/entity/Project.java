package com.example.projectedgecarftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String summery;
    private String gitHubLink;
    private String date;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
