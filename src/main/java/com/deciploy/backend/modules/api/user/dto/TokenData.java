package com.deciploy.backend.modules.api.user.dto;

public record TokenData(
        String token,
        String expiration
) {
}
