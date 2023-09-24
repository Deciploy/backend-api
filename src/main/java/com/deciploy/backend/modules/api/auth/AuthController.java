package com.deciploy.backend.modules.api.auth;

import com.deciploy.backend.modules.api.auth.dto.LoginRequest;
import com.deciploy.backend.modules.api.auth.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(registerRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode().value()).body(e.getReason());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode().value()).body(e.getReason());
        }
    }

}
