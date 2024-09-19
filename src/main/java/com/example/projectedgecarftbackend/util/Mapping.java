package com.example.projectedgecarftbackend.util;

import com.example.projectedgecarftbackend.dto.ProjectDTO;
import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.entity.Project;
import com.example.projectedgecarftbackend.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public User userDtoToUser (UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }

    public UserDTO userToUserDto (User user){
        return modelMapper.map(user,UserDTO.class);
    }

    public Project projectDtoToProject (ProjectDTO projectDTO){
        return modelMapper.map(projectDTO,Project.class);
    }

    public ProjectDTO projectToProjectDto (Project project){
        return modelMapper.map(project,ProjectDTO.class);
    }

}
