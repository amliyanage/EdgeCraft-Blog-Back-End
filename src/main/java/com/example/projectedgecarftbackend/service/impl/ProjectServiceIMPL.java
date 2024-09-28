package com.example.projectedgecarftbackend.service.impl;
import com.example.projectedgecarftbackend.dto.ProjectDTO;
import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.entity.Project;
import com.example.projectedgecarftbackend.entity.User;
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
import java.util.List;
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

    @Override
    public ProjectDTO getProject(String projectId) {
        Project project = projectRepository.findByProjectId(projectId);
        if (project != null){
            return mapping.projectToProjectDto(project);
        }else {
            return null;
        }
    }

    @Override
    public List<ProjectDTO> getAllProjects(UserDTO userDTO) {
        User user = mapping.userDtoToUser(userDTO);
        List<Project> allProjectByUser = projectRepository.getAllProjectByUser(user);
        return mapping.convertToProjectDTOList(allProjectByUser);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> all = projectRepository.findAll();
        return mapping.convertToProjectDTOList(all);
    }

    @Override
    public String deleteProject(String projectId) throws IOException {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()){
            deleteOldThumbnails(project.get().getProjectTitle());
            projectRepository.delete(project.get());
            return "Project Delete Successful";
        }else {
            return "Project Delete Fail";
        }
    }

    @Override
    public ProjectDTO getLastProject() {
        Optional<Project> project = projectRepository.getLastProject();
        System.out.println(project);
        if (project.isPresent()){
            return mapping.projectToProjectDto(project.get());
        }else {
            return null;
        }
    }

    public void deleteOldThumbnails(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/static/projectThumbnail/" + fileName +".jpg");
        Files.delete(path);
    }

}
