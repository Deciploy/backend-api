package com.deciploy.backend.modules.api.application.repository;

import com.deciploy.backend.modules.api.application.entity.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, String> {

    Optional<ApplicationType> findById(String id);
}
