package com.paintingscollectors.user.service;

import com.paintingscollectors.user.model.User;
import com.paintingscollectors.user.repository.UserRepository;
import com.paintingscollectors.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void registerUser(@Valid RegisterRequest registerRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(registerRequest.getUsername());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User is already registered");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(encodedPassword)
                .email(registerRequest.getEmail())
                .build();

        userRepository.save(user);
    }
}
