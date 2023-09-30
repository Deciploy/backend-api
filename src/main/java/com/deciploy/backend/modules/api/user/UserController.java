package com.deciploy.backend.modules.api.user;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.user.dto.CreateUserRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User API")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.saveUser(createUserRequest);
        return ApiResponse.success("User registered successfully");
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return ApiResponse.data(userService.findAll());
    }
}
