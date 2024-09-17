package com.example.project_edgecraft_back_end.service.impl;

import com.example.project_edgecraft_back_end.dto.UserDTO;
import com.example.project_edgecraft_back_end.repository.UserRepository;
import com.example.project_edgecraft_back_end.service.UserService;
import com.example.project_edgecraft_back_end.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper mapper;

    private static final String UPLOAD_DIR = "/com/example/project_edgecraft_back_end/util/profilePic/";

    @Override
    public String saveUser(UserDTO userDTO) {
        System.out.println(userDTO);
        if (userDTO != null){
            if (userRepository.existsByEmail(userDTO.getEmail())){
                return "Email already exists";
            }
            else {
                if (userRepository.existsByUserName(userDTO.getUserName())){
                    return "User already exists";
                }
                else {
                    userRepository.save(mapper.userDtoToUser(userDTO));
                    return "Registration Successful";
                }
            }
        } else {
            return "Error";
        }
    }
}
