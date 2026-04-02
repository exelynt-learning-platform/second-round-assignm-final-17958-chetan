package com.multigenesys.ecommerce.service;

import com.multigenesys.ecommerce.dto.LoginBody;
import com.multigenesys.ecommerce.dto.RegisterRequest;
import com.multigenesys.ecommerce.entity.User;
import com.multigenesys.ecommerce.exception.UserWithEmailNotExistException;
import com.multigenesys.ecommerce.repository.UserRepository;
import com.multigenesys.ecommerce.security.JwtUtil;
import com.multigenesys.ecommerce.util.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public String login(LoginBody body) {
        User user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(() -> new UserWithEmailNotExistException("User not Exist"));

        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid Password");
            return null;
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
