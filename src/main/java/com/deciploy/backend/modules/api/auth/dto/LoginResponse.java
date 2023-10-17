package com.deciploy.backend.modules.api.auth.dto;

import com.deciploy.backend.modules.api.user.entity.User;
import com.deciploy.backend.modules.jwt.TokenData;

public record LoginResponse(
        TokenData token,
        User user
) {
}
