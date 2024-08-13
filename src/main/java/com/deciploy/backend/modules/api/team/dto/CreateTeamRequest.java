package com.deciploy.backend.modules.api.team.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTeamRequest(
        @NotBlank
        String name,

        @NotBlank
        String description
) {
}
