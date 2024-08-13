package com.deciploy.backend.modules.api.time.dto;

import jakarta.validation.constraints.NotBlank;

public record ClockInRequest(
        @NotBlank
        String time
) {
}
