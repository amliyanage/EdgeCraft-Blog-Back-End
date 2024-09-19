package com.example.project_edgecraft_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDTO {
    private String projectId;
    private String description;
    private String summery;
    private String githubLink;
    private String userType;
    private String userId;
}
