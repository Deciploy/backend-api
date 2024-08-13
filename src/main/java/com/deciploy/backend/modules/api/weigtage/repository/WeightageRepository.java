package com.deciploy.backend.modules.api.weigtage.repository;

import com.deciploy.backend.modules.api.application.entity.ApplicationType;
import com.deciploy.backend.modules.api.team.entity.Team;
import com.deciploy.backend.modules.api.weigtage.entity.ApplicationTypeWeightage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface WeightageRepository extends JpaRepository<ApplicationTypeWeightage, String>, QuerydslPredicateExecutor<ApplicationTypeWeightage> {

    Optional<ApplicationTypeWeightage> findWeightageByApplicationTypeAndTeam(ApplicationType applicationType, Team team);

    @Transactional
    default ApplicationTypeWeightage create(ApplicationTypeWeightage weightage) {
        Optional<ApplicationTypeWeightage> existingWeightage = findWeightageByApplicationTypeAndTeam(weightage.getApplicationType(), weightage.getTeam());

        if (existingWeightage.isPresent()) {
            existingWeightage.get().setWeightage(weightage.getWeightage());
            return save(existingWeightage.get());
        }

        return save(weightage);
    }

}
