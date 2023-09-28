package com.deciploy.backend.modules.api.auth;

import com.deciploy.backend.modules.api.auth.dto.LoginRequest;
import com.deciploy.backend.modules.api.auth.dto.RegisterRequest;
import com.deciploy.backend.modules.api.auth.dto.RegisterResponse;
import com.deciploy.backend.modules.api.common.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication API")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ApiResponse.success("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ApiResponse.data(new RegisterResponse(token));
    }

}
