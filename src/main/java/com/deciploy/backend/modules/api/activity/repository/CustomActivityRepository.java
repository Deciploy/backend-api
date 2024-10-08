package com.deciploy.backend.modules.api.activity.repository;

import com.deciploy.backend.modules.api.activity.dto.DateCompanyScore;
import com.deciploy.backend.modules.api.activity.dto.EmployeeScore;
import com.deciploy.backend.modules.api.activity.dto.TeamScore;
import com.deciploy.backend.modules.api.company.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomActivityRepository {
    List<EmployeeScore> getEmployeeScores();

    List<EmployeeScore> getEmployeeScores(Date date, boolean isFrom);

    List<EmployeeScore> getEmployeeScores(Date from, Date to);

    List<TeamScore> getTeamScores();

    List<TeamScore> getTeamScores(Date date, boolean isFrom);

    List<TeamScore> getTeamScores(Date from, Date to);

    List<DateCompanyScore> getCompanyScores(Company company);

    List<DateCompanyScore> getCompanyScores(Company company, Date date, boolean isFrom);

    List<DateCompanyScore> getCompanyScores(Company company, Date from, Date to);

}
