package com.example.userservice.controller;

import com.example.userservice.DTO.*;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO requestDTO){
        User user = userService.signUp(requestDTO.getName(),
                requestDTO.getEmail(),
                requestDTO.getPassword());

        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        responseDTO.setUserName(user.getUserName());
        return responseDTO;
    }

    @PostMapping("/login")
    public LoginReponseDTO login(@RequestBody LoginRequestDTO requestDTO){
        Token token = userService.login(
                requestDTO.getEmail(),
                requestDTO.getPassword());

        LoginReponseDTO responseDTO = new LoginReponseDTO();
        responseDTO.setToken(token);
        return responseDTO;
    }

    /* Why we are using post Mapping instead of Get Mapping? */
    @PostMapping("/validate")
    public UserDTO validate(@RequestHeader("Authorization") String token) {
        User user = userService.validate(token);
        return UserDTO.fromUser(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutRequestDTO requestDTO){
        userService.logout(requestDTO.getToken().getValue());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
