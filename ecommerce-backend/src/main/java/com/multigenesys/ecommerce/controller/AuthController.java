package com.multigenesys.ecommerce.controller;

import com.multigenesys.ecommerce.dto.LoginBody;
import com.multigenesys.ecommerce.dto.LoginResponse;
import com.multigenesys.ecommerce.dto.RegisterRequest;
import com.multigenesys.ecommerce.dto.UserDto;
import com.multigenesys.ecommerce.entity.User;
import com.multigenesys.ecommerce.service.AuthService;
import com.multigenesys.ecommerce.util.Mapper;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request){
        User user = authService.register(request);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toUserDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginBody body){
        String token = authService.login(body);
        LoginResponse loginResponse = new LoginResponse();

        if (token == null){
            loginResponse.setError("Password Not Match");
            loginResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }

        loginResponse.setJwt(token);
        loginResponse.setSuccess(true);

        return ResponseEntity.ok(loginResponse);

    }
}
