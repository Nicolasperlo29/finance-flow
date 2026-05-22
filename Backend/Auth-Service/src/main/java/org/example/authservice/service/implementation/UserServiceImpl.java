package org.example.authservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.UserResponse;
import org.example.authservice.entity.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("No se encontro el usuario");
        }

        return new UserResponse(userId, user.get().getEmail(), user.get().getName());
    }
}
