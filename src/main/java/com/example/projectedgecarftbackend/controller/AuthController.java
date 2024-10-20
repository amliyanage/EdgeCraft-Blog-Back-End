package com.example.projectedgecarftbackend.controller;

import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.exception.DataPersistFailedException;
import com.example.projectedgecarftbackend.jwtmodels.JwtAuthResponse;
import com.example.projectedgecarftbackend.jwtmodels.SignIn;
import com.example.projectedgecarftbackend.service.AuthenticationService;
import com.example.projectedgecarftbackend.util.AppInit;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("edge_craft/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JwtAuthResponse> signUp(
            @RequestPart("userName") String userName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("role") String role,
            @RequestPart("profilePic")MultipartFile profilePic) {
        try{
            System.out.println("Awaa User Name: "+userName);
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(AppInit.generateUserId());
            userDTO.setUserName(userName);
            userDTO.setRole(role);
            userDTO.setEmail(email);
            userDTO.setPassword(passwordEncoder.encode(password));
            userDTO.setStatus("Active");

//            String response = userService.register(userDTO);
            JwtAuthResponse jwtAuthResponse = authenticationService.signUp(userDTO);
            if (jwtAuthResponse != null){
                saveUploadedFile(profilePic,userName);
                return new ResponseEntity<>(jwtAuthResponse,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
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

}
