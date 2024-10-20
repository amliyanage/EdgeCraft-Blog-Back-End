package com.example.projectedgecarftbackend.service;

import com.example.projectedgecarftbackend.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    String register(UserDTO userDTO);

    String login(String email, String password);

    UserDTO getUserData(String email);

    boolean updateUser(String email, String password, String username);

    UserDetailsService userDetailsService();
}
