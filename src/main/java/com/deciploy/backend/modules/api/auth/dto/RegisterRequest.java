package com.deciploy.backend.modules.api.auth.dto;

public record RegisterRequest(String fullName, String email, String password, String[] roles) {
}
