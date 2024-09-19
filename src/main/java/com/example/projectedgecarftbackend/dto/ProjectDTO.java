package com.example.projectedgecarftbackend.dto;

import com.example.projectedgecarftbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String projectId;
    private String description;
    private String summery;
    private String gitHubLink;
    private String userType;
    private User user;
}
