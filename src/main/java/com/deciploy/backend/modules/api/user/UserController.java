package com.deciploy.backend.modules.api.user;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.user.dto.CreateUserRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "User", description = "User API")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("api/user")
@Secured({"MANAGER", "ADMIN"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.saveUser(createUserRequest);
        return ApiResponse.success("User registered successfully");
    }

    @GetMapping()
    public ResponseEntity findAll(
            @RequestParam Optional<String> teamId
    ) {
        return ApiResponse.data(userService.findAll(teamId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.updateUser(id, createUserRequest);
        return ApiResponse.success("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.success("User deleted successfully");
    }

}
