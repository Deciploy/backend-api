package com.deciploy.backend.modules.api.application.repository;

import com.deciploy.backend.modules.api.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    Optional<Application> findByIdentifier(String identifier);

    @Query("SELECT a FROM Application a WHERE a.type.id = ?1")
    List<Application> findByType(String type);
}
