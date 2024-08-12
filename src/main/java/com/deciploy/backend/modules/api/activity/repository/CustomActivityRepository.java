package com.deciploy.backend.modules.api.activity.repository;

import com.deciploy.backend.modules.api.activity.dto.EmployeeScore;
import com.deciploy.backend.modules.api.activity.dto.TeamScore;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomActivityRepository {
    List<EmployeeScore> getEmployeeScores();

    List<EmployeeScore> getEmployeeScores(Date date, boolean isFrom);

    List<EmployeeScore> getEmployeeScores(Date from, Date to);

    List<TeamScore> getTeamScores();

}
