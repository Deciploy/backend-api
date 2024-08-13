package com.deciploy.backend.modules.api.decision.dto;

import com.deciploy.backend.modules.api.time.dto.WorkTime;
import com.deciploy.backend.modules.api.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkTime {
    private User employee;

    private List<WorkTime> workTime;
}
