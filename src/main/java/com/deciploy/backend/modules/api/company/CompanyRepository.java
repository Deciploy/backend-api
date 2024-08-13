package com.deciploy.backend.modules.api.company;

import com.deciploy.backend.modules.api.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
