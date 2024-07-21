package com.deciploy.backend.modules.api.time;

import com.deciploy.backend.modules.api.time.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, String> {

    @Query("SELECT t FROM Time t WHERE t.user.id = :userId AND t.clockOut IS NULL")
    List<Time> getClockedInTimeToClockOutTime(String userId);
}
