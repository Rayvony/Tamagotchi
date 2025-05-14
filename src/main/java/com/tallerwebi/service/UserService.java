package com.tallerwebi.service;

import com.tallerwebi.models.User;
import com.tallerwebi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setValidateToken(UUID.randomUUID().toString());
        user.setValidated(false);

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.isValidated()) {
            throw new RuntimeException("El usuario no ha validado su email");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }

    public void validateUser(String validateToken) {
        User user = userRepository.findByValidateToken(validateToken)
                .orElseThrow(() -> new RuntimeException("Token de validación no válido"));

        user.setValidated(true);
        userRepository.save(user);
    }
}