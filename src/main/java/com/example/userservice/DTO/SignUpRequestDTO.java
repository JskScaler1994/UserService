package com.example.userservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String name;
    private String password;
    private String email;
}
