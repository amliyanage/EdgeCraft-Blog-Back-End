package com.example.projectedgecarftbackend.service.impl;
import com.example.projectedgecarftbackend.dto.ProjectDTO;
import com.example.projectedgecarftbackend.entity.Project;
import com.example.projectedgecarftbackend.repository.ProjectRepository;
import com.example.projectedgecarftbackend.service.ProjectService;
import com.example.projectedgecarftbackend.util.AppInit;
import com.example.projectedgecarftbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceIMPL implements ProjectService {

    @Autowired
    private Mapping mapping;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public String saveProject(ProjectDTO projectDTO) {
        while (projectRepository.existsByProjectId(projectDTO.getProjectId())){
            projectDTO.setProjectId(AppInit.generateProjectId());
        }

        if (projectRepository.save(mapping.projectDtoToProject(projectDTO)) != null){
            return "Project Save Successful";
        }else {
            return "Registration Fail";
        }
    }

    @Override
    public String updateProject(ProjectDTO projectDTO) throws IOException {
        Optional<Project> project = projectRepository.findById(projectDTO.getProjectId());
        if (project.isPresent()){
            deleteOldThumbnails(project.get().getProjectTitle());
            project.get().setProjectTitle(projectDTO.getProjectTitle());
            project.get().setProjectType(projectDTO.getProjectType());
            project.get().setDescription(projectDTO.getDescription());
            project.get().setSummery(projectDTO.getSummery());
            project.get().setGitHubLink(projectDTO.getGitHubLink());
            project.get().setDate(projectDTO.getDate());
            project.get().setProjectStatus(projectDTO.getProjectStatus());
            return "Project Update Successful";
        }else {
            return "Project Update Fail";
        }
    }

    public void deleteOldThumbnails(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/static/projectThumbnail/" + fileName +".jpg");
        Files.delete(path);
    }

}
