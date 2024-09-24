package com.example.projectedgecarftbackend.service;

import com.example.projectedgecarftbackend.dto.ProjectDTO;

import java.io.IOException;

public interface ProjectService {
    String saveProject(ProjectDTO projectDTO);

    String updateProject(ProjectDTO projectDTO) throws IOException;
}
