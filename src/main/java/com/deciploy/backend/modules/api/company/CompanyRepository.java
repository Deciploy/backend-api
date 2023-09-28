package com.deciploy.backend.modules.api.company;

import com.deciploy.backend.modules.api.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
