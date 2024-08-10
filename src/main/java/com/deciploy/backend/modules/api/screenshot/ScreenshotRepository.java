package com.deciploy.backend.modules.api.screenshot;

import com.deciploy.backend.modules.api.screenshot.entity.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScreenshotRepository extends JpaRepository<Screenshot, String> {
    @Query("SELECT a FROM Screenshot a WHERE a.user.id = :userId ORDER BY a.capturedAt DESC")
    List<Screenshot> findScreenshotByUserId(@Param("userId") String userId);

    @Query("SELECT a FROM Screenshot a WHERE a.user.id = :userId AND a.capturedAt <= :to ORDER BY a.capturedAt DESC")
    List<Screenshot> findByUserIdAndCapturedAtBefore(String userId, Date to);

    @Query("SELECT a FROM Screenshot a WHERE a.user.id = :userId AND a.capturedAt >= :from ORDER BY a.capturedAt DESC")
    List<Screenshot> findByUserIdAndCapturedAtAfter(String userId, Date from);

    @Query("SELECT a FROM Screenshot a WHERE a.user.id = :userId AND a.capturedAt >= :from AND a.capturedAt <= :to ORDER BY a.capturedAt DESC")
    List<Screenshot> findByUserIdBetween(String userId, Date from, Date to);
}
