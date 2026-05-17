package org.example.authservice.service;

import org.example.authservice.dto.AuthResponse;
import org.example.authservice.dto.LoginRequest;
import org.example.authservice.dto.RegisterRequest;
import org.example.authservice.dto.UserResponse;
import org.example.authservice.entity.User;

import java.util.List;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    List<User> getUsers();
}
