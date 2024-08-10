package com.deciploy.backend.modules.api.time.repository;

import com.deciploy.backend.modules.api.time.dto.WorkTime;
import com.deciploy.backend.modules.api.user.entity.User;

import java.util.Date;
import java.util.List;

public interface CustomTimeRepository {
    List<WorkTime> getWorkTimeByEmployee(User employee);

    List<WorkTime> getWorkTimeByEmployee(User employee, Date date, boolean isFrom);

    List<WorkTime> getWorkTimeByEmployee(User employee, Date from, Date to);
}
