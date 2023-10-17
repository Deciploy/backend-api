package com.deciploy.backend.modules.api.auth;

import com.deciploy.backend.modules.api.auth.dto.LoginRequest;
import com.deciploy.backend.modules.api.auth.dto.LoginResponse;
import com.deciploy.backend.modules.api.auth.dto.RegisterRequest;
import com.deciploy.backend.modules.api.user.CustomerUserDetailsService;
import com.deciploy.backend.modules.api.user.UserService;
import com.deciploy.backend.modules.api.user.entity.User;
import com.deciploy.backend.modules.jwt.JwtTokenProvider;
import com.deciploy.backend.modules.jwt.TokenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        if (userService.getUserByEmail(registerRequest.email()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
        }

        userService.saveUser(
                registerRequest.fullName(),
                registerRequest.email(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.roles()
        );
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.email());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email does not exist");
        }

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }

        TokenData token = jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities());

        return new LoginResponse(token, user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }
    }
}
