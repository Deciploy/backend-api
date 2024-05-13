package com.deciploy.backend.modules.api.auth;

import com.deciploy.backend.modules.api.auth.dto.LoginRequest;
import com.deciploy.backend.modules.api.auth.dto.LoginResponse;
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

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest, "ADMIN");
        return ApiResponse.data(response);
    }

    @PostMapping("/login/employee")
    public ResponseEntity loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest, "EMPLOYEE");
        return ApiResponse.data(response);
    }

    @PostMapping("/login/manger")
    public ResponseEntity loginManager(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest, "MANAGER");
        return ApiResponse.data(response);
    }

}
