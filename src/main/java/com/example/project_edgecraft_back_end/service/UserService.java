package com.example.project_edgecraft_back_end.service;

import com.example.project_edgecraft_back_end.dto.UserDTO;

public interface UserService {
    String saveUser(UserDTO userDTO);

    String loginUser(String email, String password);

    UserDTO getUser(String email);

    boolean updateUser(String email, String password, String userName);
}
