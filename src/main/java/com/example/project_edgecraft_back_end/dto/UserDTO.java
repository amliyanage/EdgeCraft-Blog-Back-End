package com.example.project_edgecraft_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String role;
}
