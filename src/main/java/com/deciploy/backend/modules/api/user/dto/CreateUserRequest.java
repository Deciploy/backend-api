package com.deciploy.backend.modules.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank() String fullName,

        @NotBlank() @Email() String email,

        @NotEmpty() @Size(min = 1) String[] roles,

        String companyId,

        String teamId
) {
}
