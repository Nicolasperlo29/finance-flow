package org.example.authservice.service;

import org.example.authservice.dto.UserResponse;

public interface UserService {

    UserResponse getUser(Long userId);
}
