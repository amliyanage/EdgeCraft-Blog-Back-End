package com.example.project_edgecraft_back_end.controller;

import com.example.project_edgecraft_back_end.dto.UserDTO;
import com.example.project_edgecraft_back_end.service.UserService;
import com.example.project_edgecraft_back_end.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/edge_craft/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    private static final String UPLOADED_FOLDER = "uploads/";

    @PostMapping
    public ResponseEntity<String> register(@RequestParam("userName") String userName,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String role,
                                           @RequestParam("file") MultipartFile file) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(Mapper.generateUserId());
        userDTO.setUserName(userName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setRole(role);

        try {
            String response = userService.saveUser(userDTO);
            if (response.equals("Registration Successful")) {
                saveUploadedFile(file,userName);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    private void saveUploadedFile(MultipartFile file,String userName) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file");
        }

        File uploadDir = new File(UPLOADED_FOLDER);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String newFilename = userName + ".jpg";

        Path path = Paths.get(UPLOADED_FOLDER + newFilename);
        Files.write(path, file.getBytes());
    }

}
