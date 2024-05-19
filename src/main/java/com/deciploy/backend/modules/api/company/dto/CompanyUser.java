package com.deciploy.backend.modules.api.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CompanyUser(
        @NotBlank() String fullName,

        @NotBlank() @Email() String email,

        @NotBlank() String password
) {
}
