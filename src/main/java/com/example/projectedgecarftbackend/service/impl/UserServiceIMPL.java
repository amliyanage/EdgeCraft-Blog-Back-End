package com.example.projectedgecarftbackend.service.impl;

import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.entity.User;
import com.example.projectedgecarftbackend.repository.UserRepository;
import com.example.projectedgecarftbackend.service.UserService;
import com.example.projectedgecarftbackend.util.AppInit;
import com.example.projectedgecarftbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Mapping mapping;

    @Override
    public String register(UserDTO userDTO){

        while (userRepository.existsByUserId(userDTO.getUserId())){
            userDTO.setUserId(AppInit.generateUserId());
        }

        if (!userRepository.existsByEmail(userDTO.getEmail())){
            if (!userRepository.existsByUserName(userDTO.getUserName())){
                User save = userRepository.save(mapping.userDtoToUser(userDTO));
                if (save != null){
                    return "User Registration Successful";
                }else {
                    return "Registration Fail";
                }
            }else {
                return "Username is Already Taken";
            }
        }else {
            return "Email is Already Taken";
        }

    }

    @Override
    public String login(String email, String password) {
        if (userRepository.existsByEmail(email)){
            if (userRepository.getPassword(email).equals(password)){
                return "login successful";
            }
            else {
                return "wrong password";
            }
        }else {
            return "can not find user";
        }
    }

    @Override
    public UserDTO getUserData(String email) {
        if (userRepository.existsByEmail(email)){
            User user = userRepository.getUserByEmail(email);
            return mapping.userToUserDto(user);
        }else {
            return null;
        }
    }

    @Override
    public boolean updateUser(String email, String password, String username) {
        int updated = userRepository.updateUser(email, password, username);
        return updated > 0;
    }

}
