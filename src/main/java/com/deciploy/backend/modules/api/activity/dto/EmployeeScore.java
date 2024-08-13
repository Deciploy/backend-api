package com.deciploy.backend.modules.api.activity.dto;

import com.deciploy.backend.modules.api.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeScore {
    private User user;
    private int score;
}
