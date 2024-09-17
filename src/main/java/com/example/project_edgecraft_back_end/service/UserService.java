package com.example.project_edgecraft_back_end.service;

import com.example.project_edgecraft_back_end.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    String saveUser(UserDTO userDTO);
}
