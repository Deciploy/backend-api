package com.deciploy.backend.modules.jwt;

public record TokenData(
        String token,
        String expiration
) {
}
