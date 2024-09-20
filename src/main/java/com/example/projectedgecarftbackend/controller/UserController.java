package com.example.projectedgecarftbackend.controller;

import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.service.UserService;
import com.example.projectedgecarftbackend.util.AppInit;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                saveUploadedFile(profilePic,userName);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void saveUploadedFile(MultipartFile file, String fileName) throws Exception {
        File uploadFile = new File("src/main/resources/static/");

        if (!uploadFile.exists()){
            uploadFile.mkdir();
        }

        String newFileName = fileName + ".jpg";
        Path path = Paths.get("src/main/resources/static/"+newFileName);
        Files.write(path,file.getBytes());
    }

    @GetMapping(value = "/login", params = {"email" , "password"})
    public ResponseEntity<String> login(@RequestParam("email")String email , @RequestParam("password")String password){
        String response = userService.login(email,password);
        if(response.equals("login successful")){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("email")String email){
        UserDTO userDTO = userService.getUserData(email);
        if (userDTO != null){
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(params = {"email" , "password","username"})
    public ResponseEntity<String> updateUser(@RequestParam("email")String email , @RequestParam("password")String password, @RequestParam("username")String username){
        boolean response = userService.updateUser(email,password,username);
        if (response){
            return new ResponseEntity<>("Update User Successful",HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Failed Update",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/profilePic/{username}")
    public ResponseEntity<Resource> getUserPic(@PathVariable("username")String username){
        try {
            Path path = Paths.get("src/main/resources/static/"+username+".jpg");
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() && resource.isReadable()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
