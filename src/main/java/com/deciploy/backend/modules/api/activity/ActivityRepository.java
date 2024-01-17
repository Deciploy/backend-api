package com.deciploy.backend.modules.api.activity;

import com.deciploy.backend.modules.api.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {
}
