package com.deciploy.backend.modules.api.screenshot.dto;

import jakarta.validation.constraints.NotBlank;

public record ScreenshotRequest(
        @NotBlank()
        String url,

        @NotBlank()
        String capturedAt
) {
}
