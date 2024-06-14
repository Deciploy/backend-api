package com.deciploy.backend.modules.api.tracking.dto;

import com.deciploy.backend.modules.api.activity.entity.Activity;
import com.deciploy.backend.modules.api.user.entity.User;

import java.util.List;

public record ActivityTracking(
        User user,
        List<Activity> activities
) {
}
