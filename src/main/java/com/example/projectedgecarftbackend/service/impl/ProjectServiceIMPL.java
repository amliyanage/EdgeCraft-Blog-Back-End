package com.example.projectedgecarftbackend.service.impl;
import com.example.projectedgecarftbackend.dto.ProjectDTO;
import com.example.projectedgecarftbackend.repository.ProjectRepository;
import com.example.projectedgecarftbackend.service.ProjectService;
import com.example.projectedgecarftbackend.util.AppInit;
import com.example.projectedgecarftbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

}
