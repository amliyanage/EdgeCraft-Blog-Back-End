package com.example.projectedgecarftbackend.dto;

import com.example.projectedgecarftbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String projectId;
    private String projectTitle;
    private String projectType;
    private String projectStatus;
    private String description;
    private String summery;
    private String gitHubLink;
    private String date;
    private User user;
    private Date createdDate;
}
