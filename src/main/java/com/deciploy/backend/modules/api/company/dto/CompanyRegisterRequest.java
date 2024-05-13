package com.deciploy.backend.modules.api.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CompanyRegisterRequest(
        @NotBlank()
        String name,

        @NotBlank()
        @Email()
        String email,

        @NotBlank()
        String address,

        @NotBlank()
        @Pattern(regexp = "^[0-9]{10}$", message = "invalid format")
        String contactNumber,

        @NotBlank()
        CompanyUser user
) {
}
