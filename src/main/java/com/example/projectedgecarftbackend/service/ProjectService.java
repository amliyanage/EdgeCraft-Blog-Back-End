package com.example.projectedgecarftbackend.service;

import com.example.projectedgecarftbackend.dto.ProjectDTO;
import com.example.projectedgecarftbackend.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
    String saveProject(ProjectDTO projectDTO);

    String updateProject(ProjectDTO projectDTO) throws IOException;

    ProjectDTO getProject(String projectId);

    List<ProjectDTO> getAllProjects(UserDTO userDTO);
}
