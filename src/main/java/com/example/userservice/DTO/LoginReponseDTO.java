package com.example.userservice.DTO;

import com.example.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReponseDTO {
    private Token token;
}
