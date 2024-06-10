package com.deciploy.backend.modules.api.activity.repository;

import com.deciploy.backend.modules.api.activity.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    Optional<Application> findByIdentifier(String identifier);
}
