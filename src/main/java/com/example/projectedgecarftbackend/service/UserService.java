package com.example.projectedgecarftbackend.service;

import com.example.projectedgecarftbackend.dto.UserDTO;

public interface UserService {
    String register(UserDTO userDTO);

    String login(String email, String password);

    UserDTO getUserData(String email);

    boolean updateUser(String email, String password, String username);
}
