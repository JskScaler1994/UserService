package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    private String userName;
    private String email;
    private String hashedPassword;
    /*Need to update fetch type*/
    @ManyToMany
    private List<Role> roles;
}
