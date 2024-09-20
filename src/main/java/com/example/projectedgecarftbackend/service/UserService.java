package com.example.projectedgecarftbackend.service;

import com.example.projectedgecarftbackend.dto.UserDTO;

public interface UserService {
    String register(UserDTO userDTO);

    String login(String email, String password);
}
