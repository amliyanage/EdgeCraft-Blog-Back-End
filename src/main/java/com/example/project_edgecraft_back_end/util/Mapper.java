package com.example.project_edgecraft_back_end.util;

import com.example.project_edgecraft_back_end.dto.UserDTO;
import com.example.project_edgecraft_back_end.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Mapper {
    public User userDtoToUser(UserDTO userDto) {
        return new User(userDto.getUserId(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword(), userDto.getRole());
    }
    public UserDTO userToUserDto(User user) {
        return new UserDTO(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getRole());
    }
    public static String generateUserId (){
        return "U-" + UUID.randomUUID().toString();
    }
}
