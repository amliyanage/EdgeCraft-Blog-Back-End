package com.example.projectedgecarftbackend.controller;

import com.example.projectedgecarftbackend.dto.ProjectDTO;
import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.service.ProjectService;
import com.example.projectedgecarftbackend.service.UserService;
import com.example.projectedgecarftbackend.util.AppInit;
import com.example.projectedgecarftbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/edge_craft/v1/projects")
@CrossOrigin("*")

public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapping mapping;

    @PostMapping
    public ResponseEntity<String> saveProject(
            @RequestPart("projectDes") String projectDes,
            @RequestPart("projectTitle") String projectTitle,
            @RequestPart("projectType") String projectType,
            @RequestPart("projectDate") String projectDate,
            @RequestPart("projectOwnerEmail") String projectOwnerEmail,
            @RequestPart("projectSummery") String projectSummery,
            @RequestPart("projectGitUrl") String projectGitUrl,
            @RequestPart("projectThumbnail") MultipartFile projectThumbnail
    ){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(AppInit.generateProjectId());
        projectDTO.setProjectTitle(projectTitle);
        projectDTO.setProjectType(projectType);
        projectDTO.setProjectStatus("Active");
        projectDTO.setDescription(projectDes);
        projectDTO.setSummery(projectSummery);
        projectDTO.setGitHubLink(projectGitUrl);
        projectDTO.setDate(projectDate);

        UserDTO userData = userService.getUserData(projectOwnerEmail);

        projectDTO.setUser(mapping.userDtoToUser(userData));

        String response = projectService.saveProject(projectDTO);
        if (response.equals("Project Save Successful")){
            try {
                saveUploadedFile(projectThumbnail,projectTitle);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Project Save Failed");
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    private void saveUploadedFile(MultipartFile file, String fileName) throws Exception {
        File uploadFile = new File("src/main/resources/static/projectThumbnail/");

        if (!uploadFile.exists()){
            uploadFile.mkdir();
        }

        String newFileName = fileName + ".jpg";
        Path path = Paths.get("src/main/resources/static/projectThumbnail/"+newFileName);
        Files.write(path,file.getBytes());
    }

}
