package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;

public interface IUserService {
    public User signUp(String name, String email, String password);
    public Token login(String email, String password);
    public User validate(String token);
    public void logout(String token);
}
