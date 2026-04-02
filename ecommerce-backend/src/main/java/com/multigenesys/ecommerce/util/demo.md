```java
package com.multigenesys.ecommerce.controller;

import com.multigenesys.ecommerce.dto.LoginBody;
import com.multigenesys.ecommerce.dto.LoginResponse;
import com.multigenesys.ecommerce.dto.RegisterRequest;
import com.multigenesys.ecommerce.dto.UserDto;
import com.multigenesys.ecommerce.entity.User;
import com.multigenesys.ecommerce.service.AuthService;
import com.multigenesys.ecommerce.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final Mapper mapper;

    public AuthController(AuthService authService, Mapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request){
        User user = authService.register(request);
        if (user != null){
            return ResponseEntity.ok(mapper.toUserDto(user));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginBody body){
        String token = authService.login(body);
        LoginResponse loginResponse = new LoginResponse();
        if (token != null){
            loginResponse.setJwt(token);
            loginResponse.setSuccess(true);
            return ResponseEntity.ok(loginResponse);

        }else {
            loginResponse.setError("Password Not Match");
            loginResponse.setSuccess(false);

            return ResponseEntity.badRequest().body(loginResponse);
        }
    }
}

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
        try{
            User user = new User();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    public String login(LoginBody body) {
        Optional<User> user = userRepository.findByEmail(body.getEmail());
        if (user.isPresent()) {
            if (!passwordEncoder.matches(body.getPassword(), user.get().getPassword())) {
//            throw new RuntimeException("Invalid Password");
                return null;
            }

            return jwtUtil.generateToken(user.get().getEmail());
        }

        throw new UserWithEmailNotExistException("User not Exist");
    }
}


```