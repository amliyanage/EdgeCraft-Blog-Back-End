package com.example.project_edgecraft_back_end.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String role;
    private String status;
}
