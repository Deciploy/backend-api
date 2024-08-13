package com.deciploy.backend.modules.api.application.repository;

import com.deciploy.backend.modules.api.application.dto.ApplicationUsage;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomApplicationRepository {
    List<ApplicationUsage> getApplicationUsage();

    List<ApplicationUsage> getApplicationUsage(Date date, boolean isFrom);

    List<ApplicationUsage> getApplicationUsage(Date from, Date to);
}
