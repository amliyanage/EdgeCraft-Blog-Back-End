package com.example.projectedgecarftbackend.service;


import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.jwtmodels.JwtAuthResponse;
import com.example.projectedgecarftbackend.jwtmodels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
