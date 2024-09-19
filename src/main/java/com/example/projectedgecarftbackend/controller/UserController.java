package com.example.projectedgecarftbackend.controller;

import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.service.UserService;
import com.example.projectedgecarftbackend.util.AppInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/edge_craft/v1/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(name = "/health")
    public String healthCheck (){
        return "User controller is okay";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register (
            @RequestPart("userName") String userName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("role") String role,
            @RequestPart("profilePic")MultipartFile profilePic
    ){
        try{
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(AppInit.generateUserId());
            userDTO.setUserName(userName);
            userDTO.setRole(role);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setStatus("Active");

            String response = userService.register(userDTO);
            if (response.equals("User Registration Successful")){
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
