package com.deciploy.backend.modules.api.activity.dto;

import jakarta.validation.constraints.NotBlank;

public record ActivitySyncRequest(
        @NotBlank()
        String name,

        @NotBlank()
        String title,

        @NotBlank()
        String startTime,

        @NotBlank()
        String endTime
) {
}
