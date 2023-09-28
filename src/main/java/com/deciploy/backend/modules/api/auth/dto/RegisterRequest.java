package com.deciploy.backend.modules.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank() String fullName,

        @NotBlank() @Email() String email,

        @NotBlank() String password,

        @NotEmpty() @Size(min = 1) String[] roles
) {
}
