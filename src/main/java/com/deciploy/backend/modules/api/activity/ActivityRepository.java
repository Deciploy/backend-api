package com.deciploy.backend.modules.api.activity;

import com.deciploy.backend.modules.api.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String> {
    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId")
    List<Activity> findActivityByUserId(@Param("userId") String userId);
}
