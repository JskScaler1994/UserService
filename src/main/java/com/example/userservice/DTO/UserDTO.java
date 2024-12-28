package com.example.userservice.DTO;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class UserDTO {
    private String userName;
    private String email;
    private List<Role> roles;

    public static UserDTO fromUser(User user) {
        if(user == null){
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.userName = user.getUserName();
        userDTO.email = user.getEmail();
        userDTO.roles = user.getRoles();

        return userDTO;
    }
}
