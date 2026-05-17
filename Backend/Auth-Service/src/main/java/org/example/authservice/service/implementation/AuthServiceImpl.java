package org.example.authservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.*;
import org.example.authservice.entity.User;
import org.example.authservice.event.UserRegisteredEvent;
import org.example.authservice.exception.UserAlreadyExistsException;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.AuthService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .verified(false)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtService.generateToken(user, 1000L * 60 * 60 * 24 * 7);
        String refreshToken = jwtService.generateToken(user, 1000L * 60 * 60 * 24 * 7);

        return new AuthResponse(token, refreshToken);
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }
}
