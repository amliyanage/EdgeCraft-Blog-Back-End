package com.example.projectedgecarftbackend.dto;

import com.example.projectedgecarftbackend.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String role;
    private String status;
    private List<Project> projects;
}
