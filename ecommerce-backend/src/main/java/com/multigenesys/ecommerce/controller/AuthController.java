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
