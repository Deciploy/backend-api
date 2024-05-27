package com.deciploy.backend.modules.api.screenshot;

import com.deciploy.backend.modules.api.screenshot.entity.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenshotRepository extends JpaRepository<Screenshot, String> {
    @Query("SELECT a FROM Screenshot a WHERE a.user.id = :userId")
    List<Screenshot> findScreenshotByUserId(@Param("userId") String userId);
}
