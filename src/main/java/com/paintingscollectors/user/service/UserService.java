package com.paintingscollectors.user.service;


import com.paintingscollectors.exception.UserAlreadyExists;
import com.paintingscollectors.painting.model.Painting;
import com.paintingscollectors.painting.service.PaintingService;
import com.paintingscollectors.user.model.User;
import com.paintingscollectors.user.repository.UserRepository;
import com.paintingscollectors.web.dto.LoginRequest;
import com.paintingscollectors.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PaintingService paintingService;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PaintingService paintingService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.paintingService = paintingService;
    }

    public User registerUser(@Valid RegisterRequest registerRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(registerRequest.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExists("User is already registered");
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User loginUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Incorrect username or password");
        }


        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect username or password");
        }

        return user;
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));

    }

    public List<Painting> getAllPaintings() {
        return paintingService.findAllPaintings();
    }
}
