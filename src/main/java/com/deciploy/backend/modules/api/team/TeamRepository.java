package com.deciploy.backend.modules.api.team;

import com.deciploy.backend.modules.api.company.entity.Company;
import com.deciploy.backend.modules.api.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByNameAndCompany(String name, Company company);

    List<Team> findAllByCompany(Company company);
}
